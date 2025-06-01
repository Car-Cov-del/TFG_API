package es.severo.TFG.service;

import es.severo.TFG.entities.Menu;
import es.severo.TFG.entities.MenuPedido;
import es.severo.TFG.entities.Pedido;
import es.severo.TFG.repository.MenuPedidoRepository;
import es.severo.TFG.repository.MenuRepository;
import es.severo.TFG.repository.PedidoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuPedidoServiceImpl implements MenuPedidoService {
    private final MenuPedidoRepository menuPedidoRepository;
    private final PedidoRepository pedidoRepository;
    private final MenuRepository menuRepository;


    public MenuPedidoServiceImpl(MenuPedidoRepository menuPedidoRepository,
                                 PedidoRepository pedidoRepository,
                                 MenuRepository menuRepository) {
        this.menuPedidoRepository = menuPedidoRepository;
        this.pedidoRepository = pedidoRepository;
        this.menuRepository = menuRepository;
    }

    @Override
    public Optional<MenuPedido> createMenuPedido(MenuPedido menuPedido, Long pedidoId, Long menuId) {
        Optional<Pedido> opPedido = pedidoRepository.findById(pedidoId);
        Optional<Menu> opMenu = menuRepository.findById(menuId);
        if (opPedido.isPresent() && opMenu.isPresent()) {
            menuPedido.setPedido(opPedido.get());
            menuPedido.setMenu(opMenu.get());
            return Optional.of(menuPedidoRepository.save(menuPedido));
        }
        return Optional.empty();
    }

    @Override
    public List<MenuPedido> getAllMenuPedidos() {
        return menuPedidoRepository.findAll();
    }

    @Override
    public Optional<MenuPedido> getMenuPedidoById(Long id) {
        return menuPedidoRepository.findById(id);
    }

    @Override
    public void deleteMenuPedido(Long id) {
        menuPedidoRepository.deleteById(id);
    }

    @Override
    public Optional<MenuPedido> updateMenuPedido(Long id, MenuPedido datos, Long pedidoId, Long menuId) {
        Optional<MenuPedido> op = menuPedidoRepository.findById(id);
        Optional<Pedido> pedido = pedidoRepository.findById(pedidoId);
        Optional<Menu> menu = menuRepository.findById(menuId);

        if (op.isPresent() && pedido.isPresent() && menu.isPresent()) {
            MenuPedido mp = op.get();
            mp.setCantidad(datos.getCantidad());
            mp.setPedido(pedido.get());
            mp.setMenu(menu.get());
            return Optional.of(menuPedidoRepository.save(mp));
        }
        return Optional.empty();
    }
}
