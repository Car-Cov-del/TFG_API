package es.severo.TFG.controller;

import es.severo.TFG.entities.Mesa;
import es.severo.TFG.service.MesaService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/mesas")
public class MesaController {

    private final MesaService service;

    public MesaController(MesaService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Mesa> crear(@RequestBody Mesa mesa) {
        return ResponseEntity.ok(service.createMesa(mesa));
    }

    @GetMapping
    public List<Mesa> listar() {
        return service.getAllMesas();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Mesa> obtener(@PathVariable Long id) {
        return service.getMesaById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Mesa mesa) {
        return service.updateMesa(id, mesa)
                .map(m -> ResponseEntity.ok("Mesa actualizada correctamente"))
                .orElse(ResponseEntity.badRequest().body("No se pudo actualizar la mesa"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getMesaById(id).isPresent()) {
            service.deleteMesa(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
