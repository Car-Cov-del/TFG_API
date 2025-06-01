package es.severo.TFG.service;

import es.severo.TFG.entities.Categoria;
import es.severo.TFG.entities.Tamano;
import es.severo.TFG.repository.CategoriaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoriaServiceImpl implements  CategoriaService{

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public Categoria createCategoria(Categoria categoria) {
        return categoriaRepository.save(categoria);
    }

    @Override
    public List<Categoria> getAllCategorias() {
        return categoriaRepository.findAll();
    }

    @Override
    public Optional<Categoria> getCategoriaById(Long id) {
        return categoriaRepository.findById(id);
    }

    @Override
    public void deleteCategoria(Long id) {
        categoriaRepository.deleteById(id);
    }

    @Override
    public Optional<Categoria> updateCategoria(Long id, Categoria categoria) {
        Optional<Categoria> opCategoria = categoriaRepository.findById(id);
        if(opCategoria.isPresent()){
            opCategoria.get().setNombre(categoria.getNombre());
            if(!categoria.getImagen().isEmpty()){
                opCategoria.get().setImagen(categoria.getImagen());
            }
            return Optional.of(categoriaRepository.save(opCategoria.get()));
        }
        return Optional.empty();
    }
}
