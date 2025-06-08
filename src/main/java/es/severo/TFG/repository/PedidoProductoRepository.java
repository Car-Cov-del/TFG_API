package es.severo.TFG.repository;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.PedidoProducto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoProductoRepository extends JpaRepository<PedidoProducto, Long> {

}
