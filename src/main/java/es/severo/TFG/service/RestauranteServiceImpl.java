package es.severo.TFG.service;

import es.severo.TFG.entities.Restaurante;
import es.severo.TFG.repository.RestauranteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteServiceImpl implements RestauranteService {

    private final RestauranteRepository restauranteRepository;

    public RestauranteServiceImpl(RestauranteRepository restauranteRepository) {
        this.restauranteRepository = restauranteRepository;
    }

    @Override
    public Restaurante createRestaurante(Restaurante restaurante) {
        return restauranteRepository.save(restaurante);
    }

    @Override
    public List<Restaurante> getAllRestaurantes() {
        return restauranteRepository.findAll();
    }

    @Override
    public Optional<Restaurante> getRestauranteById(Long id) {
        return restauranteRepository.findById(id);
    }

    @Override
    public Restaurante updateRestaurante(Long id, Restaurante nuevosDatos) {
        return restauranteRepository.findById(id)
                .map(r -> {
                    r.setNombre(nuevosDatos.getNombre());
                    r.setCalle(nuevosDatos.getCalle());
                    r.setTelefono(nuevosDatos.getTelefono());
                    r.setCif(nuevosDatos.getCif());
                    r.setEmail(nuevosDatos.getEmail());
                    r.setWeb(nuevosDatos.getWeb());
                    return restauranteRepository.save(r);
                }).orElseThrow(() -> new RuntimeException("Restaurante no encontrado"));
    }

    @Override
    public void deleteRestaurante(Long id) {
        restauranteRepository.deleteById(id);
    }
}
