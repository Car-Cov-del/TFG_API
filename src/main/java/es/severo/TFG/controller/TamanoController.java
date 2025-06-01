package es.severo.TFG.controller;

import es.severo.TFG.entities.Tamano;
import es.severo.TFG.service.TamanoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/tamanos")
public class TamanoController {

    private final TamanoService service;

    public TamanoController(TamanoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Tamano> crear(@RequestBody Tamano tamano) {
        return ResponseEntity.ok(service.createTamano(tamano));
    }

    @GetMapping
    public List<Tamano> listar() {
        return service.getAllTamanos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tamano> obtener(@PathVariable Long id) {
        return service.getTamanoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Tamano tamano) {
        return service.updateTamano(id, tamano)
                .map(t -> ResponseEntity.ok("Tamaño actualizado correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar el tamaño"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getTamanoById(id).isPresent()) {
            service.deleteTamano(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
