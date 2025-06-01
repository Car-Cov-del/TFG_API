package es.severo.TFG.repository;

import es.severo.TFG.entities.Especificacion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecificacionRepository extends JpaRepository<Especificacion, Long> {
    List<Especificacion> findByPedidoProductoId(Long pedidoProductoId);
}
