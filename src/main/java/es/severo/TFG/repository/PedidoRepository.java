package es.severo.TFG.repository;

import es.severo.TFG.entities.Pedido;
import es.severo.TFG.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {

    @Query("SELECT MAX(p.numeroPedidoDelDia) FROM Pedido p WHERE DATE(p.fecha) = :fecha")
    Optional<Integer> findMaxNumeroPedidoDelDia(@Param("fecha") LocalDate fecha);

    @Query("SELECT pp.producto " +
            "FROM PedidoProducto pp " +
            "GROUP BY pp.producto " +
            "ORDER BY SUM(pp.cantidad) DESC")
    List<Producto> findTopProductos(Pageable pageable);

    List<Pedido> findByEstado(String estado);
}
