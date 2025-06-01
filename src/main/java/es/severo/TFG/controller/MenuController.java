package es.severo.TFG.controller;

import es.severo.TFG.entities.Menu;
import es.severo.TFG.service.MenuService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/TFG/menus")
public class MenuController {

    private final MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody Menu menu) {
        return ResponseEntity.ok(menuService.createMenu(menu));
    }

    @GetMapping
    public List<Menu> listar() {
        return menuService.getAllMenus();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Menu> obtener(@PathVariable Long id) {
        return menuService.getMenuById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody Menu datos) {
        if (menuService.getMenuById(id).isPresent() &&
                menuService.updateMenu(id, datos).isPresent()) {
            return ResponseEntity.ok().body("El menú ha sido actualizado correctamente");
        }
        return ResponseEntity.badRequest().body("No se pudo actualizar el menú");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (menuService.getMenuById(id).isPresent()) {
            menuService.deleteMenu(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

