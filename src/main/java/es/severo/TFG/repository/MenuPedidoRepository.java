package es.severo.TFG.repository;

import es.severo.TFG.entities.MenuPedido;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuPedidoRepository extends JpaRepository<MenuPedido, Long> {
    List<MenuPedido> findByPedidoId(Long pedidoId);
}
