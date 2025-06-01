package es.severo.TFG.service;

import es.severo.TFG.entities.Pedido;
import es.severo.TFG.entities.PedidoProducto;

import java.util.List;
import java.util.Optional;

public interface PedidoProductoService {
    Optional<PedidoProducto> createPedidoProducto(PedidoProducto pedidoProducto, Long pedidoId, Long productoId);
    List<PedidoProducto> getAllPedidoProductos();
    Optional<PedidoProducto> getPedidoProductoById(Long id);
    void deletePedidoProducto(Long id);
    Optional<PedidoProducto> updatePedidoProducto(Long id, PedidoProducto pedidoProducto, Long pedidoId, Long productoId);
}
