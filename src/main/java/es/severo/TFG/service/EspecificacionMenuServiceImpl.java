package es.severo.TFG.service;

import es.severo.TFG.entities.CategoriaMenu;
import es.severo.TFG.entities.EspecificacionMenu;
import es.severo.TFG.entities.MenuPedido;
import es.severo.TFG.entities.Producto;
import es.severo.TFG.repository.CategoriaMenuRepository;
import es.severo.TFG.repository.EspecificacionMenuRepository;
import es.severo.TFG.repository.MenuPedidoRepository;
import es.severo.TFG.repository.ProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecificacionMenuServiceImpl implements EspecificacionMenuService {
    private final EspecificacionMenuRepository especificacionMenuRepository;
    private final ProductoRepository productoRepository;
    private final MenuPedidoRepository menuPedidoRepository;
    private final CategoriaMenuRepository categoriaMenuRepository;


    public EspecificacionMenuServiceImpl(EspecificacionMenuRepository especificacionMenuRepository,
                                         ProductoRepository productoRepository,
                                         MenuPedidoRepository menuPedidoRepository,
                                         CategoriaMenuRepository categoriaMenuRepository) {
        this.especificacionMenuRepository = especificacionMenuRepository;
        this.productoRepository = productoRepository;
        this.menuPedidoRepository = menuPedidoRepository;
        this.categoriaMenuRepository = categoriaMenuRepository;
    }

    @Override
    public Optional<EspecificacionMenu> create(EspecificacionMenu especificacionMenu, Long menuPedidoId, Long productoId, Long categoriaMenuId) {
        var opMenuPedido = menuPedidoRepository.findById(menuPedidoId);
        var opProducto = productoRepository.findById(productoId);
        var opCategoriaMenu = categoriaMenuRepository.findById(categoriaMenuId);

        if (opMenuPedido.isPresent() && opProducto.isPresent() && opCategoriaMenu.isPresent()) {
            especificacionMenu.setPedidoMenu(opMenuPedido.get());
            especificacionMenu.setProducto(opProducto.get());
            especificacionMenu.setCategoriaMenu(opCategoriaMenu.get());
            return Optional.of(especificacionMenuRepository.save(especificacionMenu));
        }
        return Optional.empty();
    }

    @Override
    public List<EspecificacionMenu> getAll() {
        return especificacionMenuRepository.findAll();
    }

    @Override
    public Optional<EspecificacionMenu> getById(Long id) {
        return especificacionMenuRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        especificacionMenuRepository.deleteById(id);
    }

    @Override
    public Optional<EspecificacionMenu> update(Long id, EspecificacionMenu datos, Long productoId, Long menuPedidoId, Long categoriaMenuId) {
        Optional<EspecificacionMenu> op = especificacionMenuRepository.findById(id);
        Optional<Producto> producto = productoRepository.findById(productoId);
        Optional<MenuPedido> menuPedido = menuPedidoRepository.findById(menuPedidoId);
        Optional<CategoriaMenu> categoria = categoriaMenuRepository.findById(categoriaMenuId);

        if (op.isPresent() && producto.isPresent() && menuPedido.isPresent() && categoria.isPresent()) {
            EspecificacionMenu em = op.get();
            em.setProducto(producto.get());
            em.setPedidoMenu(menuPedido.get());
            em.setCategoriaMenu(categoria.get());
            em.setCantidad(datos.getCantidad());
            return Optional.of(especificacionMenuRepository.save(em));
        }
        return Optional.empty();
    }

}

