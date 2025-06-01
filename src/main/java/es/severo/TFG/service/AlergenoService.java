package es.severo.TFG.service;

import es.severo.TFG.entities.Alergeno;

import java.util.List;
import java.util.Optional;

public interface AlergenoService {
    Alergeno createAlergeno(Alergeno alergeno);
    List<Alergeno> getAllAlergenos();
    Optional<Alergeno> getAlergenoById(Long id);
    void deleteAlergeno(Long id);
    Optional<Alergeno> updateAlergeno(Long id, Alergeno datos);
}
