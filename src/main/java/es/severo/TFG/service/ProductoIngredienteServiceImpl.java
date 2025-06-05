package es.severo.TFG.service;

import es.severo.TFG.entities.Ingrediente;
import es.severo.TFG.entities.Producto;
import es.severo.TFG.entities.ProductoIngrediente;
import es.severo.TFG.entities.ProductoIngredienteId;
import es.severo.TFG.repository.IngredienteRepository;
import es.severo.TFG.repository.ProductoIngredienteRepository;
import es.severo.TFG.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoIngredienteServiceImpl implements ProductoIngredienteService {

    private final ProductoIngredienteRepository repository;
    private final ProductoRepository productoRepository;
    private final IngredienteRepository ingredienteRepository;

    public ProductoIngredienteServiceImpl(
            ProductoIngredienteRepository repository,
            ProductoRepository productoRepository,
            IngredienteRepository ingredienteRepository
    ) {
        this.repository = repository;
        this.productoRepository = productoRepository;
        this.ingredienteRepository = ingredienteRepository;
    }

    @Override
    public Optional<ProductoIngrediente> createProductoIngrediente(ProductoIngrediente productoIngrediente, Long productoId, Long ingredienteId) {
        Optional<Producto> opProducto = productoRepository.findById(productoId);
        Optional<Ingrediente> opIngrediente = ingredienteRepository.findById(ingredienteId);

        if (opProducto.isPresent() && opIngrediente.isPresent()) {
            productoIngrediente.setProducto(opProducto.get());
            productoIngrediente.setIngrediente(opIngrediente.get());

            ProductoIngredienteId id = new ProductoIngredienteId(productoId, ingredienteId);
            productoIngrediente.setId(id);

            return Optional.of(repository.save(productoIngrediente));
        }

        return Optional.empty();
    }

    @Override
    public List<ProductoIngrediente> getAllProductoIngredientes() {
        return repository.findAll();
    }

    @Override
    public Optional<ProductoIngrediente> getProductoIngredienteById(ProductoIngredienteId id) {
        return repository.findById(id);
    }

    @Override
    public void deleteProductoIngrediente(ProductoIngredienteId id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<ProductoIngrediente> updateProductoIngrediente(ProductoIngredienteId id, ProductoIngrediente data) {
        Optional<ProductoIngrediente> opt = repository.findById(id);

        if (opt.isPresent()) {
            ProductoIngrediente pi = opt.get();
            pi.setPrecioExtra(data.getPrecioExtra());
            pi.setObligatorio(data.getObligatorio());
            return Optional.of(repository.save(pi));
        }

        return Optional.empty();
    }

    @Override
    public List<ProductoIngrediente> getByProductoId(Long productoId) {
        return repository.findByProducto_Id(productoId);
    }

    @Override
    public void deleteProductoIngrediente(Long productoId, Long ingredienteId) {
        repository.deleteByProducto_IdAndIngrediente_Id(productoId, ingredienteId);
    }

    @Override
    public Optional<ProductoIngrediente> updateProductoIngrediente(Long productoId, Long ingredienteId, ProductoIngrediente data) {
        ProductoIngredienteId id = new ProductoIngredienteId(productoId, ingredienteId);
        Optional<ProductoIngrediente> opt = repository.findById(id);

        if (opt.isPresent()) {
            ProductoIngrediente pi = opt.get();
            pi.setPrecioExtra(data.getPrecioExtra());
            pi.setObligatorio(data.getObligatorio());
            return Optional.of(repository.save(pi));
        }

        return Optional.empty();
    }

}
