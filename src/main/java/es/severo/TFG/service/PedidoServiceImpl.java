package es.severo.TFG.service;

import es.severo.TFG.entities.*;
import es.severo.TFG.repository.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.awt.print.Pageable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;

    private final RestauranteRepository restauranteRepository;

    private final ProductoRepository productoRepository;
    private final PedidoProductoRepository pedidoProductoRepository;
    private final IngredienteRepository ingredienteRepository;
    private final EspecificacionRepository especificacionRepository;


    public PedidoServiceImpl(PedidoRepository pedidoRepository, MesaRepository mesaRepository, RestauranteRepository restauranteRepository, ProductoRepository productoRepository, PedidoProductoRepository pedidoProductoRepository, IngredienteRepository ingredienteRepository, EspecificacionRepository especificacionRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;

        this.restauranteRepository = restauranteRepository;
        this.productoRepository = productoRepository;
        this.pedidoProductoRepository = pedidoProductoRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.especificacionRepository = especificacionRepository;
    }

    public Optional<Pedido> createPedido(Pedido pedido, Long mesaId) {
        System.out.println("DEBUG: Entrada a Pedido" + pedido);
        System.out.println("DEBUG: Entrada a Mesa" + mesaId);
        // Asigno la fecha actual
        pedido.setFecha(LocalDateTime.now());

        // Asigno el estado por defecto
        pedido.setEstado("preparando");

        // Obtengo el numero de pedido, se reinician cada dia
        // (Empiezan por el 10 porque no soy capaz de concebir un kebab llamando al numero 7 o algo asi, son siempre numeros de 2 digitos)
        int numeroHoy = pedidoRepository.findMaxNumeroPedidoDelDia(LocalDate.now())
                .map(n -> n + 1)
                .orElse(10);
        pedido.setNumeroPedidoDelDia(numeroHoy);

        // Si se proporciona una mesa, buscarla y asignarla
        if (mesaId != null) {
            Optional<Mesa> opMesa = mesaRepository.findById(mesaId);
            if (opMesa.isEmpty() || !opMesa.get().getEstado().equalsIgnoreCase("Libre")) {
                System.out.println("DEBUG: Mesa inválida o no libre");
                return Optional.empty();
            }
            Mesa mesa = opMesa.get();
            pedido.setMesa(mesa);

            // Cambio el estado de la mesa a ocupada
            mesa.setEstado("Ocupada");
            mesaRepository.save(mesa);
        }

        pedido.setPrecioTotal(pedido.getPrecioTotal());

        System.out.println("DEBUG: Precio total calculado: " + pedido.getPrecioTotal());

        // Si el método de pago es tarjeta y no se ha pagado, asumo el pago completo exacto
        if ("tarjeta".equalsIgnoreCase(pedido.getMetodoPago()) &&
                (pedido.getPrecioPagado() == null || pedido.getPrecioPagado() == 0)) {
            System.out.println("DEBUG: Precio pagado es cero o nulo, asignando total");
            pedido.setPrecioPagado(pedido.getPrecioTotal());
        }

        System.out.println("DEBUG: precioPagado antes de guardar: " + pedido.getPrecioPagado());

        // Establezco el restaurante (Se supone que solo hay 1)
        Restaurante restaurante = restauranteRepository.findById(1L).orElseThrow();
        pedido.setRestaurante(restaurante);

        // Guardar el Pedido y establezco las relaciones
        Pedido savedPedido = pedidoRepository.save(pedido);

        for (PedidoProducto pp : pedido.getProductos()) {
            pp.setPedido(savedPedido);
            pp.setProducto(productoRepository.findById(pp.getProducto().getId()).orElseThrow());

            PedidoProducto savedPP = pedidoProductoRepository.save(pp);

            if (pp.getEspecificaciones() != null) {
                for (Especificacion e : pp.getEspecificaciones()) {
                    e.setPedidoProducto(savedPP);
                    e.setIngrediente(ingredienteRepository.findById(e.getIngrediente().getId()).orElseThrow());
                    especificacionRepository.save(e);
                }
            }
        }

        System.out.println("DEBUG: Pedido guardado con id: " + savedPedido.getId() +
                ", precioPagado: " + savedPedido.getPrecioPagado());

        // Generaro el ticket
        savedPedido.setTicket(generarTicket(savedPedido));

        pedidoRepository.save(savedPedido);

        return Optional.of(savedPedido);
    }

    private String generarTicket(Pedido pedido) {
        Restaurante r = pedido.getRestaurante();
        String fecha = pedido.getFecha().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        String numero = String.format("%05d", pedido.getNumeroPedidoDelDia());

        StringBuilder ticketBuilder = new StringBuilder();

        // DATOS DEL RESTAURANTE
        ticketBuilder.append("========= ").append(r.getNombre()).append(" =========\n");
        ticketBuilder.append(r.getCalle()).append("\n");
        ticketBuilder.append("Tel: ").append(r.getTelefono()).append("\n");
        ticketBuilder.append("Web: ").append(r.getWeb()).append("\n");

        ticketBuilder.append("\n----------------------------\n");

        // NUMERO Y FECHA
        ticketBuilder.append("PEDIDO N° ").append(numero).append("\n");
        ticketBuilder.append("Fecha: ").append(fecha).append("\n");

        ticketBuilder.append("----------------------------\n");

        // PRODUCTOS
        ticketBuilder.append("Productos:\n");
        for (PedidoProducto pp : pedido.getProductos()) {
            ticketBuilder.append("- ").append(pp.getProducto().getNombre())
                    .append(" x").append(pp.getCantidad())
                    .append(" (").append(pp.getProducto().getPrecioBase()).append("€ c/u)\n");

            if (pp.getEspecificaciones() != null && !pp.getEspecificaciones().isEmpty()) {
                ticketBuilder.append("  Especificaciones:\n");
                for (Especificacion e : pp.getEspecificaciones()) {
                    ticketBuilder.append("   * ").append(e.getAccion())
                            .append(" ").append(e.getIngrediente().getNombre())
                            .append(" (").append(e.getPrecioExtra()).append("€)\n");
                }
            }
        }

        ticketBuilder.append("----------------------------\n");

        // PRECIOS
        ticketBuilder.append(String.format("Total: %.2f €\n", pedido.getPrecioTotal()));
        ticketBuilder.append(String.format("Pagado: %.2f € (%s)\n",
                pedido.getPrecioPagado(),
                pedido.getMetodoPago() != null ? pedido.getMetodoPago() : "Desconocido"));

        ticketBuilder.append("----------------------------\n");

        // MENSAJE FINAL
        ticketBuilder.append("-----¡Gracias por su visita!-----\n");

        return ticketBuilder.toString();
    }

    @Override
    public List<Pedido> getAllPedidos() {
        return pedidoRepository.findAll();
    }

    @Override
    public Optional<Pedido> getPedidoById(Long id) {
        return pedidoRepository.findById(id);
    }

    @Override
    public Optional<Pedido> updatePedido(Long id, Pedido pedido, Long mesaId) {
        Optional<Mesa> opMesa = mesaRepository.findById(mesaId);
        Optional<Pedido> opPedido = pedidoRepository.findById(id);
        if(opMesa.isPresent() && opPedido.isPresent()){
            if(opMesa.get().getEstado().equalsIgnoreCase("Libre")){
                opPedido.get().setTipo(opPedido.get().getTipo());
                opPedido.get().setEstado(opPedido.get().getEstado());
                opPedido.get().setFecha(LocalDateTime.now());
                opPedido.get().setMesa(opMesa.get());
                return Optional.of(pedidoRepository.save(opPedido.get()));
            }
        }
        return Optional.empty();
    }

    @Override
    public void deletePedido(Long id) {
        pedidoRepository.deleteById(id);
    }

    @Override
    public List<Producto> getTopProductos() {
        List<Producto> top =  pedidoRepository.findTopProductos(PageRequest.of(0, 28));
        System.out.println("TOP VENTAS:");
        System.out.println(top);
        return top;
    }

    @Override
    public boolean enviarPedido(Long id) {
        Optional<Pedido> optPedido = pedidoRepository.findById(id);
        if (optPedido.isPresent()) {
            Pedido pedido = optPedido.get();

            String tipo = pedido.getTipo().toLowerCase();
            switch (tipo) {
                case "llevar":
                    pedido.setEstado("enviado");
                    break;
                case "aquí":
                case "aqui":
                    pedido.setEstado("entregado");
                    break;
                default:
                    return false;
            }

            pedidoRepository.save(pedido);
            return true;
        }
        return false;

    }

}
