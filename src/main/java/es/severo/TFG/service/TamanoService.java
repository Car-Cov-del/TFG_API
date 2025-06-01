package es.severo.TFG.service;

import es.severo.TFG.entities.Mesa;
import es.severo.TFG.entities.Tamano;

import java.util.List;
import java.util.Optional;

public interface TamanoService {
    Tamano createTamano(Tamano tamano);
    List<Tamano> getAllTamanos();
    Optional<Tamano> getTamanoById(Long id);
    void deleteTamano(Long id);
    Optional<Tamano> updateTamano(Long id, Tamano tamano);
}
