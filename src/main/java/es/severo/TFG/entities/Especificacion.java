package es.severo.TFG.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "especificaciones")
public class Especificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "ingrediente_id", nullable = false)
    @JsonIgnore
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "pedido_producto_id", nullable = false)
    @JsonIgnore
    private PedidoProducto pedidoProducto;

    @ManyToOne
    @JoinColumn(name = "pedido_menu_producto_id")
    @JsonIgnore
    private EspecificacionMenu pedidoMenuProducto;

    @Column(name = "accion", nullable = false)
    private String accion;

    @Column(name = "precio_extra", nullable = false)
    private Double precioExtra;

    @PrePersist
    @PreUpdate
    private void validarReferencias() {
        if (pedidoProducto == null && pedidoMenuProducto == null) {
            throw new IllegalStateException("Debe estar presente al menos una de las dos relaciones: productosEnPedido o pedidoMenuProducto.");
        }
    }
}
