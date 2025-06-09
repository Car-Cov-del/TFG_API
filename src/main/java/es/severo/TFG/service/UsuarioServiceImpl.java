package es.severo.TFG.service;

import es.severo.TFG.entities.Usuario;
import es.severo.TFG.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Usuario> findAll() {
        return usuarioRepository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return usuarioRepository.findById(id).orElse(null);
    }

    @Override
    public Usuario save(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    public Usuario update(Long id, Usuario nuevosDatos) {
        Usuario existente = usuarioRepository.findById(id).orElse(null);
        if (existente == null) {
            throw new IllegalArgumentException("Usuario no encontrado");
        }

        existente.setNombre(nuevosDatos.getNombre());
        existente.setContrasena(nuevosDatos.getContrasena());
        existente.setRol(nuevosDatos.getRol());

        return usuarioRepository.save(existente);
    }


    @Override
    public void deleteById(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null && usuario.getRol().equalsIgnoreCase("administrador")) {
            long administradores = countAdministradores();
            if (administradores <= 1) {
                throw new IllegalStateException("No se puede eliminar el último administrador.");
            }
        }
        usuarioRepository.deleteById(id);
    }

    @Override
    public long countAdministradores() {
        return usuarioRepository.countByRolIgnoreCase("administrador");
    }
}
