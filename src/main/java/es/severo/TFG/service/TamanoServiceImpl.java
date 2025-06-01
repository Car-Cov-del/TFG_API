package es.severo.TFG.service;

import es.severo.TFG.entities.Mesa;
import es.severo.TFG.entities.Tamano;
import es.severo.TFG.repository.TamanoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TamanoServiceImpl implements TamanoService{

    private final TamanoRepository tamanoRepository;

    public TamanoServiceImpl(TamanoRepository tamanoRepository) {
        this.tamanoRepository = tamanoRepository;
    }

    @Override
    public Tamano createTamano(Tamano tamano) {
        return tamanoRepository.save(tamano);
    }

    @Override
    public List<Tamano> getAllTamanos() {
        return tamanoRepository.findAll();
    }

    @Override
    public Optional<Tamano> getTamanoById(Long id) {
        return tamanoRepository.findById(id);
    }

    @Override
    public void deleteTamano(Long id) {
        tamanoRepository.deleteById(id);
    }

    @Override
    public Optional<Tamano> updateTamano(Long id, Tamano tamano) {
        Optional<Tamano> opTamano = tamanoRepository.findById(id);
        if(opTamano.isPresent()){
            opTamano.get().setNombre(tamano.getNombre());
            return Optional.of(tamanoRepository.save(opTamano.get()));
        }
        return Optional.empty();
    }
}
