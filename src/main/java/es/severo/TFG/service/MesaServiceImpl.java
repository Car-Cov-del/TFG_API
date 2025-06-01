package es.severo.TFG.service;

import es.severo.TFG.entities.Alergeno;
import es.severo.TFG.entities.Mesa;
import es.severo.TFG.repository.MesaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MesaServiceImpl implements MesaService{

    private final MesaRepository mesaRepository;

    public MesaServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public Mesa createMesa(Mesa mesa) {
        return mesaRepository.save(mesa);
    }

    @Override
    public List<Mesa> getAllMesas() {
        return mesaRepository.findAll();
    }

    @Override
    public Optional<Mesa> getMesaById(Long id) {
        return mesaRepository.findById(id);
    }

    @Override
    public void deleteMesa(Long id) {
        mesaRepository.deleteById(id);
    }

    @Override
    public Optional<Mesa> updateMesa(Long id, Mesa mesa) {
        Optional<Mesa> opMesa = mesaRepository.findById(id);
        if(opMesa.isPresent()){
            opMesa.get().setNombre(mesa.getNombre());
            opMesa.get().setEstado(mesa.getEstado());
            opMesa.get().setNumSitios(mesa.getNumSitios());
            return Optional.of(mesaRepository.save(opMesa.get()));
        }
        return Optional.empty();
    }
}
