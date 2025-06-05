package es.severo.TFG.service;

import es.severo.TFG.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Usuario createUsuario(Usuario usuario, Long restauranteId);
    List<Usuario> getAllUsuarios();
    Optional<Usuario> getUsuarioById(Long id);
    Usuario updateUsuario(Long id, Usuario usuario, Long restauranteId);
    void deleteUsuario(Long id);
}

