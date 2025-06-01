package es.severo.TFG.service;

import es.severo.TFG.entities.MenuPedido;

import java.util.List;
import java.util.Optional;

public interface MenuPedidoService {
    Optional<MenuPedido> createMenuPedido(MenuPedido menuPedido, Long pedidoId, Long menuId);
    List<MenuPedido> getAllMenuPedidos();
    Optional<MenuPedido> getMenuPedidoById(Long id);
    void deleteMenuPedido(Long id);
    Optional<MenuPedido> updateMenuPedido(Long id, MenuPedido datos, Long pedidoId, Long menuId);
}
