package es.severo.TFG.controller;

import es.severo.TFG.entities.EspecificacionMenu;
import es.severo.TFG.entities.MenuPedido;
import es.severo.TFG.service.MenuPedidoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/TFG/menu-pedido")
public class MenuPedidoController {

    private final MenuPedidoService service;

    public MenuPedidoController(MenuPedidoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody MenuPedido menuPedido,
                                   @RequestParam Long pedidoId,
                                   @RequestParam Long menuId) {
        Optional<MenuPedido> r = service.createMenuPedido(menuPedido, pedidoId, menuId);
        if (r.isPresent()) {
            return ResponseEntity.status(HttpStatus.CREATED).body(r.get());
        }
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public List<MenuPedido> listar() {
        return service.getAllMenuPedidos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuPedido> obtener(@PathVariable Long id) {
        return service.getMenuPedidoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id,
                                        @RequestBody MenuPedido datos,
                                        @RequestParam Long pedidoId,
                                        @RequestParam Long menuId) {
        if (service.getMenuPedidoById(id).isPresent() &&
                service.updateMenuPedido(id, datos, pedidoId, menuId).isPresent()) {
            return ResponseEntity.ok().body("MenuPedido actualizado correctamente");
        }
        return ResponseEntity.badRequest().body("Error al actualizar el MenuPedido");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        if (service.getMenuPedidoById(id).isPresent()) {
            service.deleteMenuPedido(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
