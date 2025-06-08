package es.severo.TFG.controller;

import es.severo.TFG.entities.Pedido;
import es.severo.TFG.entities.Producto;
import es.severo.TFG.repository.PedidoProductoRepository;
import es.severo.TFG.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service, PedidoProductoRepository pedidoProductoRepository) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crearPedido(@RequestBody Pedido pedido,
                                         @RequestParam(required = false) Long mesaId) {
        Optional<Pedido> result = service.createPedido(pedido, mesaId);
        if (result.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(result.get());
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No se pudo crear el pedido");
    }


    @GetMapping
    public List<Pedido> listar() {
        return service.getAllPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> obtener(@PathVariable Long id) {
        return service.getPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Pedido pedido,
                                        @RequestParam Long mesaId) {
        return service.updatePedido(id, pedido, mesaId)
                .map(p -> ResponseEntity.ok("Pedido actualizado correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar el pedido. Verifica que la mesa esté libre."));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getPedidoById(id).isPresent()) {
            service.deletePedido(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint para conseguir los productos mas vendidos
    @GetMapping("/top")
    public ResponseEntity<List<Producto>> getTopProductos() {
        return ResponseEntity.ok(service.getTopProductos());
    }

    // Endpoint para enviar el pedido según su tipo y actualizar estado
    @PutMapping("/{id}/enviar")
    public ResponseEntity<String> enviarPedido(@PathVariable Long id) {
        boolean enviado = service.enviarPedido(id);

        if (!enviado) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Pedido no encontrado o tipo inválido.");
        }

        return ResponseEntity.ok("Pedido enviado/entregado correctamente.");
    }

}

