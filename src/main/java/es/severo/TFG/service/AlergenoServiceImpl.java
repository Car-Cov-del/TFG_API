package es.severo.TFG.service;

import es.severo.TFG.entities.Alergeno;
import es.severo.TFG.repository.AlergenoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AlergenoServiceImpl implements AlergenoService{

    private final AlergenoRepository repository;

    public AlergenoServiceImpl(AlergenoRepository repository) {
        this.repository = repository;
    }

    @Override
    public Alergeno createAlergeno(Alergeno alergeno) {
        return repository.save(alergeno);
    }

    @Override
    public List<Alergeno> getAllAlergenos() {
        return repository.findAll();
    }

    @Override
    public Optional<Alergeno> getAlergenoById(Long id) {
        return repository.findById(id);
    }

    @Override
    public void deleteAlergeno(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Optional<Alergeno> updateAlergeno(Long id, Alergeno alergeno) {
        Optional<Alergeno> opAlergeno = repository.findById(id);
        if(opAlergeno.isPresent()){
            opAlergeno.get().setNombre(alergeno.getNombre());
            opAlergeno.get().setDescripcion(alergeno.getDescripcion());
            if(!alergeno.getImagen().isEmpty()){
                opAlergeno.get().setImagen(alergeno.getImagen());
            }
            return Optional.of(repository.save(opAlergeno.get()));
        }
        return Optional.empty();
    }
}
