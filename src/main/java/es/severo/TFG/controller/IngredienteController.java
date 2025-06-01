package es.severo.TFG.controller;

import es.severo.TFG.dto.IngredienteDTO;
import es.severo.TFG.entities.Ingrediente;
import es.severo.TFG.service.IngredienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/ingredientes")
public class IngredienteController {

    private final IngredienteService service;

    public IngredienteController(IngredienteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crearIngrediente(@RequestBody IngredienteDTO dto) {
        return ResponseEntity.ok(service.createIngrediente(dto));
    }

    @GetMapping
    public List<Ingrediente> listar() {
        return service.getAllIngredientes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Ingrediente> obtener(@PathVariable(name = "id") Long id) {
        Optional<Ingrediente> opIngrediente = service.getIngredienteById(id);
        return opIngrediente.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable(name = "id") Long id, @RequestBody IngredienteDTO dto) {
        if (service.getIngredienteById(id).isPresent() &&
                service.updateIngrediente(id, dto).isPresent()) {
            return ResponseEntity.ok().body("El ingrediente ha sido actualizado correctamente");
        }
        return ResponseEntity.badRequest().body("El ingrediente no existe");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable(name = "id") Long id) {
        if (service.getIngredienteById(id).isPresent()) {
            service.deleteIngrediente(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}