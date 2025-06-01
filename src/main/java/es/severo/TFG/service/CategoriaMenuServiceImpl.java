package es.severo.TFG.service;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.CategoriaMenu;
import es.severo.TFG.entities.Menu;
import es.severo.TFG.repository.CategoriaMenuRepository;
import es.severo.TFG.repository.CategoriaRepository;
import es.severo.TFG.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaMenuServiceImpl implements CategoriaMenuService {
    private final CategoriaMenuRepository repo;
    private final MenuRepository menuRepository;
    private final CategoriaRepository categoriaRepository;

    public CategoriaMenuServiceImpl(CategoriaMenuRepository repo,
                                    MenuRepository menuRepository,
                                    CategoriaRepository categoriaRepository) {
        this.repo = repo;
        this.menuRepository = menuRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Optional<CategoriaMenu> create(CategoriaMenu cm, Long menuId, Long categoriaId) {
        Optional<Menu> menu = menuRepository.findById(menuId);
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);
        if (menu.isPresent() && categoria.isPresent()) {
            cm.setMenu(menu.get());
            cm.setCategoria(categoria.get());
            return Optional.of(repo.save(cm));
        }
        return Optional.empty();
    }

    @Override
    public List<CategoriaMenu> getAll() {
        return repo.findAll();
    }

    @Override
    public Optional<CategoriaMenu> getById(Long id) {
        return repo.findById(id);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Optional<CategoriaMenu> update(Long id, CategoriaMenu datos, Long menuId, Long categoriaId) {
        Optional<CategoriaMenu> op = repo.findById(id);
        Optional<Menu> menu = menuRepository.findById(menuId);
        Optional<Categoria> categoria = categoriaRepository.findById(categoriaId);

        if (op.isPresent() && menu.isPresent() && categoria.isPresent()) {
            CategoriaMenu cm = op.get();
            cm.setMenu(menu.get());
            cm.setCategoria(categoria.get());
            cm.setNombreSeccion(datos.getNombreSeccion());
            cm.setCantidad(datos.getCantidad());
            cm.setObligatorio(datos.getObligatorio());
            return Optional.of(repo.save(cm));
        }
        return Optional.empty();
    }
}
