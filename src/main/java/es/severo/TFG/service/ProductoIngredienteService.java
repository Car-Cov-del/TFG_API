package es.severo.TFG.service;

import es.severo.TFG.entities.ProductoIngrediente;
import es.severo.TFG.entities.ProductoIngredienteId;

import java.util.List;
import java.util.Optional;

public interface ProductoIngredienteService {

    Optional<ProductoIngrediente> createProductoIngrediente(ProductoIngrediente productoIngrediente, Long productoId, Long ingredienteId);
    List<ProductoIngrediente> getAllProductoIngredientes();
    Optional<ProductoIngrediente> getProductoIngredienteById(ProductoIngredienteId id);
    void deleteProductoIngrediente(ProductoIngredienteId id);
    Optional<ProductoIngrediente> updateProductoIngrediente(
            ProductoIngredienteId id,
            ProductoIngrediente data
    );
    List<ProductoIngrediente> getByProductoId(Long productoId);
    void deleteProductoIngrediente(Long productoId, Long ingredienteId);
    Optional<ProductoIngrediente> updateProductoIngrediente(Long productoId, Long ingredienteId, ProductoIngrediente data);

}
