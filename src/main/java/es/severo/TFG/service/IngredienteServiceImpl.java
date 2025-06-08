package es.severo.TFG.service;

import es.severo.TFG.dto.IngredienteDTO;
import es.severo.TFG.entities.Alergeno;
import es.severo.TFG.entities.Ingrediente;
import es.severo.TFG.repository.AlergenoRepository;
import es.severo.TFG.repository.IngredienteRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class IngredienteServiceImpl implements IngredienteService{

    private final IngredienteRepository ingredienteRepository;
    private final AlergenoRepository alergenoRepository;

    public IngredienteServiceImpl(IngredienteRepository ingredienteRepository, AlergenoRepository alergenoRepository) {
        this.ingredienteRepository = ingredienteRepository;
        this.alergenoRepository = alergenoRepository;
    }

    @Override
    @Transactional
    public Ingrediente createIngrediente(IngredienteDTO dto) {
        Ingrediente ingrediente = new Ingrediente();
        ingrediente.setNombre(dto.getNombre());
        ingrediente.setImagen(dto.getImagen());
        ingrediente.setEsAnadible(dto.getEsAnadible());
        Set<Alergeno> alergenos = new HashSet<>(alergenoRepository.findAllById(dto.getAlergenoIds()));
        ingrediente.setAlergenos(alergenos);

        return ingredienteRepository.save(ingrediente);
    }

    @Override
    public List<Ingrediente> getAllIngredientes() {
        return ingredienteRepository.findAll();
    }

    @Override
    public Optional<Ingrediente> getIngredienteById(Long id) {
        return ingredienteRepository.findById(id);
    }

    @Override
    public void deleteIngrediente(Long id) {
        ingredienteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Optional<Ingrediente> updateIngrediente(Long id, IngredienteDTO dto) {
        return ingredienteRepository.findById(id).map(existing -> {
            existing.setNombre(dto.getNombre());
            existing.setEsAnadible(dto.getEsAnadible());
            if (dto.getImagen() != null && !dto.getImagen().isEmpty()) {
                existing.setImagen(dto.getImagen());
            }

            Set<Alergeno> alergenos = new HashSet<>(alergenoRepository.findAllById(dto.getAlergenoIds()));
            existing.setAlergenos(alergenos);

            return ingredienteRepository.save(existing);
        });
    }
}
