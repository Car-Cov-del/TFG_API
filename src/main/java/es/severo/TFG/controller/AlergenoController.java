package es.severo.TFG.controller;


import es.severo.TFG.entities.Alergeno;
import es.severo.TFG.service.AlergenoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/alergenos")
public class AlergenoController {

    private final AlergenoService service;

    public AlergenoController(AlergenoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> createAlergeno(@RequestBody Alergeno alergeno) {
        return ResponseEntity.ok(service.createAlergeno(alergeno));
    }

    @GetMapping
    public List<Alergeno> listar() {
        return service.getAllAlergenos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Alergeno> obtener(@PathVariable(name = "id") Long id) {
        Optional<Alergeno> opAlergeno = service.getAlergenoById(id);
        return opAlergeno.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable(name = "id") Long id, @RequestBody Alergeno alergeno) {

        if(service.getAlergenoById(id).isPresent() && service.updateAlergeno(id, alergeno).isPresent()){
            return ResponseEntity.ok().body("El alergeno ha sido cambiado correctamente");
        }
        return ResponseEntity.badRequest().body("El alergeno no existe");

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id") Long id) {
        if (service.getAlergenoById(id).isPresent()) {
            service.deleteAlergeno(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}