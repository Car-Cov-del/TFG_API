package es.severo.TFG.repository;

import es.severo.TFG.entities.Menu;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuRepository extends JpaRepository<Menu, Long> {
    List<Menu> findByActivoTrue();
}