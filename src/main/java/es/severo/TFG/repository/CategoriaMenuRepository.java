package es.severo.TFG.repository;

import es.severo.TFG.entities.CategoriaMenu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaMenuRepository extends JpaRepository<CategoriaMenu, Long> {
    List<CategoriaMenu> findByMenuId(Long menuId);
}
