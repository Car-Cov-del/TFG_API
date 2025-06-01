package es.severo.TFG.controller;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.service.CategoriaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/categorias")
public class CategoriaController {

    private final CategoriaService service;

    public CategoriaController(CategoriaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(service.createCategoria(categoria));
    }

    @GetMapping
    public List<Categoria> listar() {
        return service.getAllCategorias();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> obtener(@PathVariable Long id) {
        return service.getCategoriaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Categoria categoria) {
        return service.updateCategoria(id, categoria)
                .map(c -> ResponseEntity.ok("Categoría actualizada correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar la categoría"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getCategoriaById(id).isPresent()) {
            service.deleteCategoria(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
