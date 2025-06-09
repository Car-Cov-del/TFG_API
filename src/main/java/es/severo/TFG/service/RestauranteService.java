package es.severo.TFG.service;

import es.severo.TFG.entities.Restaurante;

import java.util.List;
import java.util.Optional;

public interface RestauranteService {
    Restaurante createRestaurante(Restaurante restaurante);
    List<Restaurante> getAllRestaurantes();
    Optional<Restaurante> getRestauranteById(Long id);
    Restaurante updateRestaurante(Long id, Restaurante restaurante);
    void deleteRestaurante(Long id);
    Restaurante findById(Long id);

}
