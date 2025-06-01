package es.severo.TFG.service;

import es.severo.TFG.entities.Menu;
import es.severo.TFG.repository.MenuRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MenuServiceImpl implements MenuService {

    private final MenuRepository menuRepository;

    public MenuServiceImpl(MenuRepository menuRepository) {
        this.menuRepository = menuRepository;
    }

    @Override
    public Optional<Menu> createMenu(Menu menu) {
        return Optional.of(menuRepository.save(menu));
    }

    @Override
    public List<Menu> getAllMenus() {
        return menuRepository.findAll();
    }

    @Override
    public Optional<Menu> getMenuById(Long id) {
        return menuRepository.findById(id);
    }

    @Override
    public Optional<Menu> updateMenu(Long id, Menu updatedMenu) {
        return menuRepository.findById(id).map(menu -> {
            menu.setNombre(updatedMenu.getNombre());
            menu.setPrecioBase(updatedMenu.getPrecioBase());
            menu.setImagen(updatedMenu.getImagen());
            menu.setActivo(updatedMenu.getActivo());
            return menuRepository.save(menu);
        });
    }

    @Override
    public void deleteMenu(Long id) {
        menuRepository.deleteById(id);
    }
}
