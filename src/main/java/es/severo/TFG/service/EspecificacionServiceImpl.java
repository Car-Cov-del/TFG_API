package es.severo.TFG.service;

import es.severo.TFG.entities.*;
import es.severo.TFG.repository.EspecificacionRepository;
import es.severo.TFG.repository.IngredienteRepository;
import es.severo.TFG.repository.PedidoProductoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EspecificacionServiceImpl implements EspecificacionService{
    private final EspecificacionRepository especificacionRepository;
    private final IngredienteRepository ingredienteRepository;
    private final PedidoProductoRepository pedidoProductoRepository;

    public EspecificacionServiceImpl(EspecificacionRepository especificacionRepository, IngredienteRepository ingredienteRepository, PedidoProductoRepository pedidoProductoRepository) {
        this.especificacionRepository = especificacionRepository;
        this.ingredienteRepository = ingredienteRepository;
        this.pedidoProductoRepository = pedidoProductoRepository;
    }

    @Override
    public Optional<Especificacion> createEspecificacion(Especificacion especificacion, Long ingredienteId, Long productoPedidoId, Long especificacionMenuId) {
        Optional<Ingrediente> opIngrediente = ingredienteRepository.findById(ingredienteId);
        Optional<PedidoProducto> opPedidoProducto = pedidoProductoRepository.findById(productoPedidoId);
        if(opIngrediente.isPresent() && (opPedidoProducto.isPresent())){
            especificacion.setIngrediente(opIngrediente.get());
            opPedidoProducto.ifPresent(especificacion::setPedidoProducto);
            return Optional.of(especificacionRepository.save(especificacion));
        }
        return Optional.empty();
    }

    @Override
    public List<Especificacion> getAllEspecificaciones() {
        return especificacionRepository.findAll();
    }

    @Override
    public Optional<Especificacion> getEspecificacionById(Long id) {
        return especificacionRepository.findById(id);
    }

    @Override
    public void deleteEspecificacion(Long id) {
        especificacionRepository.deleteById(id);
    }

    @Override
    public Optional<Especificacion> updateEspecificacion(Long id, Especificacion especificacion, Long ingredienteId, Long productoPedidoId, Long especificacionMenuId) {
        Optional<Especificacion> opEspecificacion = especificacionRepository.findById(id);
        Optional<Ingrediente> opIngrediente = ingredienteRepository.findById(ingredienteId);
        Optional<PedidoProducto> opPedidoProducto = pedidoProductoRepository.findById(productoPedidoId);
        if(opEspecificacion.isPresent() && opIngrediente.isPresent() && (opPedidoProducto.isPresent())){
            opEspecificacion.get().setAccion(especificacion.getAccion());
            opEspecificacion.get().setPrecioExtra(especificacion.getPrecioExtra());
            opEspecificacion.get().setIngrediente(opIngrediente.get());
            opPedidoProducto.ifPresent(opEspecificacion.get()::setPedidoProducto);
            return Optional.of(especificacionRepository.save(opEspecificacion.get()));
        }
        return Optional.empty();
    }
}
