package es.severo.TFG.service;

import es.severo.TFG.entities.*;
import es.severo.TFG.repository.PedidoProductoRepository;
import es.severo.TFG.repository.PedidoRepository;
import es.severo.TFG.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PedidoProductoServiceImpl implements PedidoProductoService{
    private final PedidoProductoRepository repository;
    private final PedidoRepository pedidoRepository;
    private final ProductoRepository productoRepository;

    public PedidoProductoServiceImpl(PedidoProductoRepository repository, PedidoRepository pedidoRepository, ProductoRepository productoRepository) {
        this.repository = repository;
        this.pedidoRepository = pedidoRepository;
        this.productoRepository = productoRepository;
    }

    @Override
    public Optional<PedidoProducto> createPedidoProducto(PedidoProducto pedidoProducto, Long pedidoId, Long productoId) {
        Optional<Producto> opProducto = productoRepository.findById(productoId);
        Optional<Pedido> opPedido = pedidoRepository.findById(pedidoId);
        if(opProducto.isPresent() && opPedido.isPresent()){
            pedidoProducto.setProducto(opProducto.get());
            pedidoProducto.setPedido(opPedido.get());
            return Optional.of(repository.save(pedidoProducto));
        }
        return Optional.empty();
    }

    @Override
    public List<PedidoProducto> getAllPedidoProductos() {
        return repository.findAll();
    }

    @Override
    public Optional<PedidoProducto> getPedidoProductoById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deletePedidoProducto(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<PedidoProducto> updatePedidoProducto(Long id, PedidoProducto pedidoProducto, Long pedidoId, Long productoId) {
        Optional<PedidoProducto> opPedidoProducto = repository.findById(id);
        Optional<Producto> opProducto = productoRepository.findById(productoId);
        Optional<Pedido> opPedido = pedidoRepository.findById(pedidoId);
        if(opPedidoProducto.isPresent() && opProducto.isPresent() && opPedido.isPresent()){
            pedidoProducto = opPedidoProducto.get();
            pedidoProducto.setProducto(opProducto.get());
            pedidoProducto.setPedido(opPedido.get());
            return Optional.of(repository.save(pedidoProducto));
        }
        return Optional.empty();
    }
}
