package es.severo.TFG.controller;

import es.severo.TFG.entities.Restaurante;
import es.severo.TFG.service.RestauranteService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/restaurantes")
public class RestauranteController {

    private final RestauranteService service;

    public RestauranteController(RestauranteService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Restaurante> crear(@RequestBody Restaurante restaurante) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createRestaurante(restaurante));
    }

    @GetMapping
    public List<Restaurante> listar() {
        return service.getAllRestaurantes();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Restaurante> obtener(@PathVariable Long id) {
        return service.getRestauranteById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Restaurante> actualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        return ResponseEntity.ok(service.updateRestaurante(id, restaurante));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteRestaurante(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unico")
    public ResponseEntity<Restaurante> getUnicoRestaurante() {
        return ResponseEntity.ok(service.getAllRestaurantes().get(0));
    }

}
