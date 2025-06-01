package es.severo.TFG.service;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.Menu;
import es.severo.TFG.entities.Producto;
import es.severo.TFG.entities.Tamano;
import es.severo.TFG.repository.CategoriaRepository;
import es.severo.TFG.repository.ProductoRepository;
import es.severo.TFG.repository.TamanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements  ProductoService{

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;
    private final TamanoRepository tamanoRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository, CategoriaRepository categoriaRepository, TamanoRepository tamanoRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
        this.tamanoRepository = tamanoRepository;
    }


    @Override
    public Optional<Producto> createProducto(Producto producto, Long categoriaId, Long tamanoId) {
        Optional<Tamano> opTamano = tamanoRepository.findById(tamanoId);

        if (opTamano.isPresent()) {
            producto.setTamano(opTamano.get());

            if (categoriaId != null && categoriaId > 0) {
                categoriaRepository.findById(categoriaId).ifPresent(producto::setCategoria);
            } else {
                producto.setCategoria(null); // sin categoría
            }

            return Optional.of(productoRepository.save(producto));
        }

        return Optional.empty();
    }


    @Override
    public List<Producto> getAllProductos() {
        return productoRepository.findAll();
    }

    @Override
    public Optional<Producto> getProductoById(Long id) {
        return productoRepository.findById(id);
    }

    @Override
    public void deleteProducto(Long id) {
        productoRepository.deleteById(id);
    }

    @Override
    public Optional<Producto> updateProducto(Long id, Producto nuevosDatos, Long categoriaId, Long tamanoId) {
        Optional<Producto> opProducto = productoRepository.findById(id);
        Optional<Tamano> opTamano = tamanoRepository.findById(tamanoId);

        if (opProducto.isPresent() && opTamano.isPresent()) {
            Producto productoExistente = opProducto.get();

            productoExistente.setNombre(nuevosDatos.getNombre());
            productoExistente.setImagen(nuevosDatos.getImagen());
            productoExistente.setPrecioBase(nuevosDatos.getPrecioBase());

            productoExistente.setTamano(opTamano.get());

            if (categoriaId != null && categoriaId > 0) {
                categoriaRepository.findById(categoriaId).ifPresent(productoExistente::setCategoria);
            } else {
                productoExistente.setCategoria(null);
            }

            return Optional.of(productoRepository.save(productoExistente));
        }

        return Optional.empty();
    }
}
