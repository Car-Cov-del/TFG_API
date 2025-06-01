package es.severo.TFG.controller;

import es.severo.TFG.entities.Especificacion;
import es.severo.TFG.entities.ProductoIngrediente;
import es.severo.TFG.service.EspecificacionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/especificaciones")
public class EspecificacionController {

    private final EspecificacionService service;

    public EspecificacionController(EspecificacionService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Especificacion especificacion,
                                   @RequestParam Long ingredienteId,
                                   @RequestParam(required = false) Long productoPedidoId,
                                   @RequestParam(required = false) Long especificacionMenuId) {
        Optional<Especificacion> r = service.createEspecificacion(especificacion, ingredienteId, productoPedidoId, especificacionMenuId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<Especificacion> listar() {
        return service.getAllEspecificaciones();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Especificacion> obtener(@PathVariable Long id) {
        return service.getEspecificacionById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody Especificacion especificacion,
                                        @RequestParam Long ingredienteId,
                                        @RequestParam(required = false) Long productoPedidoId,
                                        @RequestParam(required = false) Long especificacionMenuId) {
        return service.updateEspecificacion(id, especificacion, ingredienteId, productoPedidoId, especificacionMenuId)
                .map(e -> ResponseEntity.ok("Especificación actualizada correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar la especificación"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getEspecificacionById(id).isPresent()) {
            service.deleteEspecificacion(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
