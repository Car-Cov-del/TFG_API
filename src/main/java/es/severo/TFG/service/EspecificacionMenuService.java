package es.severo.TFG.service;

import es.severo.TFG.entities.EspecificacionMenu;

import java.util.List;
import java.util.Optional;

public interface EspecificacionMenuService {
    Optional<EspecificacionMenu> create(EspecificacionMenu em, Long productoId, Long menuPedidoId, Long categoriaMenuId);
    List<EspecificacionMenu> getAll();
    Optional<EspecificacionMenu> getById(Long id);
    void delete(Long id);
    Optional<EspecificacionMenu> update(Long id, EspecificacionMenu datos, Long productoId, Long menuPedidoId, Long categoriaMenuId);
}
