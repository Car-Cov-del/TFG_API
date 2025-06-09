package es.severo.TFG.controller;

import es.severo.TFG.entities.Restaurante;
import es.severo.TFG.entities.Usuario;
import es.severo.TFG.service.RestauranteService;
import es.severo.TFG.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/usuarios")
public class UsuarioController {


    private final UsuarioService usuarioService;
    private final RestauranteService restauranteService;

    public UsuarioController(UsuarioService usuarioService, RestauranteService restauranteService) {
        this.usuarioService = usuarioService;
        this.restauranteService = restauranteService;
    }

    @GetMapping
    public List<Usuario> getAll() {
        return usuarioService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Usuario> getById(@PathVariable Long id) {
        Usuario usuario = usuarioService.findById(id);
        return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<Usuario> create(@RequestBody Usuario usuario, @RequestParam Long restauranteId) {
        Restaurante restaurante = restauranteService.findById(restauranteId);
        if (restaurante == null) {
            return ResponseEntity.badRequest().body(null);
        }
        usuario.setRestaurante(restaurante);
        Usuario creado = usuarioService.save(usuario);
        return ResponseEntity.ok(creado);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Usuario> update(@PathVariable Long id, @RequestBody Usuario usuario) {
        Usuario existente = usuarioService.findById(id);
        if (existente == null) return ResponseEntity.notFound().build();

        Usuario actualizado = usuarioService.update(id, usuario);
        return ResponseEntity.ok(actualizado);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            usuarioService.deleteById(id);
            return ResponseEntity.ok().build();
        } catch (IllegalStateException ex) {
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }
}
