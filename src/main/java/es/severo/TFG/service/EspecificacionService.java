package es.severo.TFG.service;

import es.severo.TFG.entities.Especificacion;
import es.severo.TFG.entities.PedidoProducto;

import java.util.List;
import java.util.Optional;

public interface EspecificacionService {

    Optional<Especificacion> createEspecificacion(Especificacion especificacion, Long ingredienteId, Long productoPedidoId, Long especificacionMenuId);
    List<Especificacion> getAllEspecificaciones();
    Optional<Especificacion> getEspecificacionById(Long id);
    void deleteEspecificacion(Long id);
    Optional<Especificacion> updateEspecificacion(Long id, Especificacion especificacion, Long ingredienteId, Long productoPedidoId, Long especificacionMenuId);
}
