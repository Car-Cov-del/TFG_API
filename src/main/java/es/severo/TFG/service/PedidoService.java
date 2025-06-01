package es.severo.TFG.service;

import es.severo.TFG.entities.Pedido;
import es.severo.TFG.entities.PedidoProducto;

import java.util.List;
import java.util.Optional;

public interface PedidoService {
    Optional<Pedido> createPedido(Pedido pedido, Long mesaId);
    List<Pedido> getAllPedidos();
    Optional<Pedido> getPedidoById(Long id);
    void deletePedido(Long id);
    Optional<Pedido> updatePedido(Long id, Pedido datos, Long mesaId);
}
