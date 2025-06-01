package es.severo.TFG.service;

import es.severo.TFG.entities.Mesa;

import java.util.List;
import java.util.Optional;

public interface MesaService {
    Mesa createMesa(Mesa mesa);
    List<Mesa> getAllMesas();
    Optional<Mesa> getMesaById(Long id);
    void deleteMesa(Long id);
    Optional<Mesa> updateMesa(Long id, Mesa mesa);
}
