package es.severo.TFG.service;

import es.severo.TFG.entities.Producto;

import java.util.List;
import java.util.Optional;

public interface ProductoService {

    Optional<Producto> createProducto(Producto producto, Long categoriaId, Long tamanoId);
    List<Producto> getAllProductos();
    Optional<Producto> getProductoById(Long id);
    void deleteProducto(Long id);
    Optional<Producto> updateProducto(Long id, Producto producto, Long categoriaId, Long tamanoId);
}
