package es.severo.TFG.repository;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
