package es.severo.TFG.service;

import es.severo.TFG.entities.CategoriaMenu;

import java.util.List;
import java.util.Optional;

public interface CategoriaMenuService {
    Optional<CategoriaMenu> create(CategoriaMenu cm, Long menuId, Long categoriaId);
    List<CategoriaMenu> getAll();
    Optional<CategoriaMenu> getById(Long id);
    void delete(Long id);
    Optional<CategoriaMenu> update(Long id, CategoriaMenu datos, Long menuId, Long categoriaId);
}

