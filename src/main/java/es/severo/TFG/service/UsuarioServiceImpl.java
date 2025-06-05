package es.severo.TFG.service;

import es.severo.TFG.entities.Restaurante;
import es.severo.TFG.entities.Usuario;
import es.severo.TFG.repository.RestauranteRepository;
import es.severo.TFG.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final RestauranteRepository restauranteRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository, RestauranteRepository restauranteRepository) {
        this.usuarioRepository = usuarioRepository;
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public Usuario createUsuario(Usuario usuario, Long restauranteId) {
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
        usuario.setRestaurante(restaurante);
        return usuarioRepository.save(usuario);
    }

    @Override
    public List<Usuario> getAllUsuarios() {
        return usuarioRepository.findAll();
    }

    @Override
    public Optional<Usuario> getUsuarioById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Usuario updateUsuario(Long id, Usuario nuevosDatos, Long restauranteId) {
        return usuarioRepository.findById(id).map(usuario -> {
            usuario.setNombre(nuevosDatos.getNombre());
            usuario.setContrasena(nuevosDatos.getContrasena());
            if (restauranteId != null) {
                restauranteRepository.findById(restauranteId).ifPresent(usuario::setRestaurante);
            }
            return usuarioRepository.save(usuario);
        }).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }

    @Override
    public void deleteUsuario(Long id) {
        usuarioRepository.deleteById(id);
    }
}
