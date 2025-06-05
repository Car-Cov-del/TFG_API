package es.severo.TFG.controller;

import es.severo.TFG.entities.Pedido;
import es.severo.TFG.service.PedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/pedidos")
public class PedidoController {

    private final PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Pedido pedido, @RequestParam Long mesaId) {
        Optional<Pedido> r = service.createPedido(pedido, mesaId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
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
}

