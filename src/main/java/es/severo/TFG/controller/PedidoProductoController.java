package es.severo.TFG.controller;

import es.severo.TFG.entities.PedidoProducto;
import es.severo.TFG.service.PedidoProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/pedido-productos")
public class PedidoProductoController {

    private final PedidoProductoService service;

    public PedidoProductoController(PedidoProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody PedidoProducto pedidoProducto,
                                   @RequestParam Long pedidoId,
                                   @RequestParam Long productoId) {
        Optional<PedidoProducto> r = service.createPedidoProducto(pedidoProducto, pedidoId, productoId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<PedidoProducto> listar() {
        return service.getAllPedidoProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoProducto> obtener(@PathVariable Long id) {
        return service.getPedidoProductoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody PedidoProducto pedidoProducto,
                                        @RequestParam Long pedidoId,
                                        @RequestParam Long productoId) {
        return service.updatePedidoProducto(id, pedidoProducto, pedidoId, productoId)
                .map(p -> ResponseEntity.ok("PedidoProducto actualizado correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar el PedidoProducto"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getPedidoProductoById(id).isPresent()) {
            service.deletePedidoProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
