package es.severo.TFG.service;

import es.severo.TFG.entities.Menu;

import java.util.List;
import java.util.Optional;

public interface MenuService {
    Optional<Menu> createMenu(Menu menu);
    List<Menu> getAllMenus();
    Optional<Menu> getMenuById(Long id);
    Optional<Menu> updateMenu(Long id, Menu menu);
    void deleteMenu(Long id);
}
