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
    private Ingrediente ingrediente;

    @ManyToOne
    @JoinColumn(name = "pedido_producto_id", nullable = false)
    @JsonIgnore
    private PedidoProducto pedidoProducto;

    @Column(name = "accion", nullable = false)
    private String accion;

    @Column(name = "precio_extra", nullable = false)
    private Double precioExtra;

}
