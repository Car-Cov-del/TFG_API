package es.severo.TFG.controller;

import es.severo.TFG.entities.CategoriaMenu;
import es.severo.TFG.entities.EspecificacionMenu;
import es.severo.TFG.service.EspecificacionMenuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/especificaciones-menu")
public class EspecificacionMenuController {

    private final EspecificacionMenuService service;

    public EspecificacionMenuController(EspecificacionMenuService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody EspecificacionMenu especificacionMenu,
                                   @RequestParam Long productoId,
                                   @RequestParam Long menuPedidoId,
                                   @RequestParam Long categoriaMenuId) {
        Optional<EspecificacionMenu> r = service.create(especificacionMenu, productoId, menuPedidoId, categoriaMenuId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<EspecificacionMenu> listar() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EspecificacionMenu> obtener(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody EspecificacionMenu datos,
                                        @RequestParam Long productoId,
                                        @RequestParam Long menuPedidoId,
                                        @RequestParam Long categoriaMenuId) {
        if (service.getById(id).isPresent() &&
                service.update(id, datos, productoId, menuPedidoId, categoriaMenuId).isPresent()) {
            return ResponseEntity.ok().body("Especificación del menú actualizada correctamente");
        }
        return ResponseEntity.badRequest().body("No se pudo actualizar la especificación del menú");
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
