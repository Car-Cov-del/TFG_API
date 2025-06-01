package es.severo.TFG.repository;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.Tamano;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TamanoRepository extends JpaRepository<Tamano, Long> {
}
