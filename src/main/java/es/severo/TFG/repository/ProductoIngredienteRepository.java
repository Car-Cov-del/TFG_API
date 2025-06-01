package es.severo.TFG.repository;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.ProductoIngrediente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

import es.severo.TFG.entities.ProductoIngrediente;
import es.severo.TFG.entities.ProductoIngredienteId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoIngredienteRepository extends JpaRepository<ProductoIngrediente, ProductoIngredienteId> {

    List<ProductoIngrediente> findByProducto_Id(Long productoId);

    void deleteByProducto_IdAndIngrediente_Id(Long productoId, Long ingredienteId);
}
