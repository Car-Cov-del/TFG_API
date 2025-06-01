package es.severo.TFG.repository;

import es.severo.TFG.entities.EspecificacionMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EspecificacionMenuRepository extends JpaRepository<EspecificacionMenu, Long> {
    List<EspecificacionMenu> findByPedidoMenuId(Long menuPedidoId);
}
