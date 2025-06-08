package es.severo.TFG.scheduler;

import es.severo.TFG.entities.Mesa;
import es.severo.TFG.entities.Pedido;
import es.severo.TFG.repository.MesaRepository;
import es.severo.TFG.repository.PedidoRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public class MesaYPedidoScheduler {

    @Autowired
    private MesaRepository mesaRepository;

    @Autowired
    private PedidoRepository pedidoRepository;

    // Se reinician el estado de las mesas y se caducan los pedidos todos los días a las 2AM
    @Scheduled(cron = "0 0 2 * * ?")
    @Transactional
    public void actualizarEstados() {
        // El cambio de lo spedidos
        List<Pedido> todos = pedidoRepository.findAll();
        List<Pedido> pedidosPreparando = todos.stream()
                .filter(p -> "preparando".equalsIgnoreCase(p.getEstado()))
                .toList();
        for (Pedido pedido : pedidosPreparando) {
            pedido.setEstado("caducado");
        }
        pedidoRepository.saveAll(pedidosPreparando);

        // El cambio de las mesas
        List<Mesa> mesas = mesaRepository.findAll();
        for (Mesa mesa : mesas) {
            if (!"Fuera de servicio".equalsIgnoreCase(mesa.getEstado())) {
                mesa.setEstado("libre");
            }
        }
        mesaRepository.saveAll(mesas);

        System.out.println("Actualización automática de estados ejecutada a las 2AM.");
    }
}
