package es.severo.TFG.service;

import es.severo.TFG.entities.Mesa;
import es.severo.TFG.entities.Pedido;
import es.severo.TFG.repository.MesaRepository;
import es.severo.TFG.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoServiceImpl implements PedidoService {
    private final PedidoRepository pedidoRepository;
    private final MesaRepository mesaRepository;


    public PedidoServiceImpl(PedidoRepository pedidoRepository, MesaRepository mesaRepository) {
        this.pedidoRepository = pedidoRepository;
        this.mesaRepository = mesaRepository;

    }

    @Override
    public Optional<Pedido> createPedido(Pedido pedido, Long mesaId) {
        Optional<Mesa> opMesa = mesaRepository.findById(mesaId);
        if(opMesa.isPresent()){
            if(opMesa.get().getEstado().equalsIgnoreCase("Libre")){
                pedido.setMesa(opMesa.get());
                return Optional.of(pedidoRepository.save(pedido));
            }
        }
        return Optional.empty();
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
}
