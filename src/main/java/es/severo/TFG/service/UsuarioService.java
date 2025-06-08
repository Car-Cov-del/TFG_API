package es.severo.TFG.service;

import es.severo.TFG.entities.Usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    List<Usuario> findAll();
    Usuario findById(Long id);
    Usuario save(Usuario usuario);
    void deleteById(Long id);
    long countAdministradores();
}

