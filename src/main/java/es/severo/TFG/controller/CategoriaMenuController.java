package es.severo.TFG.controller;

import es.severo.TFG.entities.CategoriaMenu;
import es.severo.TFG.service.CategoriaMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/categorias-menu")
public class CategoriaMenuController {

    private final CategoriaMenuService service;

    public CategoriaMenuController(CategoriaMenuService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody CategoriaMenu categoriaMenu,
                                   @RequestParam(name = "menuId") Long menuId,
                                   @RequestParam(name = "categoriaId") Long categoriaId) {
        Optional<CategoriaMenu> r = service.create(categoriaMenu, menuId, categoriaId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<CategoriaMenu> listar() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoriaMenu> obtener(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody CategoriaMenu datos,
                                        @RequestParam Long menuId,
                                        @RequestParam Long categoriaId) {
        if (service.getById(id).isPresent() &&
                service.update(id, datos, menuId, categoriaId).isPresent()) {
            return ResponseEntity.ok().body("Categoría del menú actualizada correctamente");
        }
        return ResponseEntity.badRequest().body("No se pudo actualizar la categoría del menú");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getById(id).isPresent()) {
            service.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
