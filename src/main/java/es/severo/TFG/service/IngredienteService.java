package es.severo.TFG.service;

import es.severo.TFG.dto.IngredienteDTO;
import es.severo.TFG.entities.Ingrediente;

import java.util.List;
import java.util.Optional;

public interface IngredienteService {
    Ingrediente createIngrediente(IngredienteDTO ingrediente);
    List<Ingrediente> getAllIngredientes();
    Optional<Ingrediente> getIngredienteById(Long id);
    void deleteIngrediente(Long id);
    Optional<Ingrediente> updateIngrediente(Long id, IngredienteDTO datos);
}
