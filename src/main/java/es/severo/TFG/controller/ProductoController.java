package es.severo.TFG.controller;

import es.severo.TFG.entities.MenuPedido;
import es.severo.TFG.entities.Pedido;
import es.severo.TFG.entities.Producto;
import es.severo.TFG.service.ProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/productos")
public class ProductoController {

    private final ProductoService service;

    public ProductoController(ProductoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto,
                                           @RequestParam Long categoriaId,
                                           @RequestParam Long tamanoId) {
        Optional<Producto> r = service.createProducto(producto, categoriaId, tamanoId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Producto> listar() {
        return service.getAllProductos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Producto> obtener(@PathVariable Long id) {
        return service.getProductoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Producto producto,
                                        @RequestParam Long categoriaId,
                                        @RequestParam Long tamanoId) {
        return service.updateProducto(id, producto, categoriaId, tamanoId)
                .map(p -> ResponseEntity.ok("Producto actualizado correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar el producto"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getProductoById(id).isPresent()) {
            service.deleteProducto(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
