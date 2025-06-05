package es.severo.TFG.controller;

import es.severo.TFG.entities.Usuario;
import es.severo.TFG.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Usuario> crear(@RequestBody Usuario usuario, @RequestParam Long restauranteId) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.createUsuario(usuario, restauranteId));
    }

    @GetMapping
    public List<Usuario> listar() {
        return service.getAllUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> obtener(@PathVariable Long id) {
        return service.getUsuarioById(id)
                .map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> actualizar(@PathVariable Long id,
                                              @RequestBody Usuario usuario,
                                              @RequestParam(required = false) Long restauranteId) {
        return ResponseEntity.ok(service.updateUsuario(id, usuario, restauranteId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        service.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
