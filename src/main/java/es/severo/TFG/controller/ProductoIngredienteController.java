package es.severo.TFG.controller;

import es.severo.TFG.entities.ProductoIngrediente;
import es.severo.TFG.entities.ProductoIngredienteId;
import es.severo.TFG.service.ProductoIngredienteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/producto-ingredientes")
public class ProductoIngredienteController {

    private final ProductoIngredienteService service;

    public ProductoIngredienteController(ProductoIngredienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody ProductoIngrediente pi,
                                   @RequestParam Long productoId,
                                   @RequestParam Long ingredienteId) {
        Optional<ProductoIngrediente> r = service.createProductoIngrediente(pi, productoId, ingredienteId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<ProductoIngrediente> listar() {
        return service.getAllProductoIngredientes();
    }

    @GetMapping("/{productoId}/{ingredienteId}")
    public ResponseEntity<ProductoIngrediente> obtener(@PathVariable Long productoId,
                                                       @PathVariable Long ingredienteId) {
        ProductoIngredienteId id = new ProductoIngredienteId(productoId, ingredienteId);
        return service.getProductoIngredienteById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{productoId}/{ingredienteId}")
    public ResponseEntity<?> actualizar(@PathVariable Long productoId,
                                        @PathVariable Long ingredienteId,
                                        @RequestBody ProductoIngrediente pi) {
        return service.updateProductoIngrediente(productoId, ingredienteId, pi)
                .map(p -> ResponseEntity.ok("Relación producto-ingrediente actualizada"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar la relación"));
    }

    @DeleteMapping("/{productoId}/{ingredienteId}")
    public ResponseEntity<?> eliminar(@PathVariable Long productoId,
                                      @PathVariable Long ingredienteId) {
        ProductoIngredienteId id = new ProductoIngredienteId(productoId, ingredienteId);

        if (service.getProductoIngredienteById(id).isPresent()) {
            service.deleteProductoIngrediente(id); // ← CORREGIDO: pasar el id compuesto
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/producto/{productoId}")
    public ResponseEntity<List<ProductoIngrediente>> getByProductoId(@PathVariable Long productoId) {
        return ResponseEntity.ok(service.getByProductoId(productoId));
    }
}
