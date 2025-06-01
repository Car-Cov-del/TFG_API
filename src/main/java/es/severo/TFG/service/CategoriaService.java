package es.severo.TFG.service;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.Mesa;

import java.util.List;
import java.util.Optional;

public interface CategoriaService {
    Categoria createCategoria(Categoria categoria);
    List<Categoria> getAllCategorias();
    Optional<Categoria> getCategoriaById(Long id);
    void deleteCategoria(Long id);
    Optional<Categoria> updateCategoria(Long id, Categoria categoria);
}
