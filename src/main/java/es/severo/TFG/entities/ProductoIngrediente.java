package es.severo.TFG.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "producto_ingrediente")
public class ProductoIngrediente {

    @EmbeddedId
    private ProductoIngredienteId id;

    @ManyToOne
    @MapsId("productoId")
    @JoinColumn(name = "productos_id", nullable = false)
    private Producto producto;

    @ManyToOne
    @MapsId("ingredienteId")
    @JoinColumn(name = "ingredientes_id", nullable = false)
    private Ingrediente ingrediente;

    @Column(name="precio_extra", nullable = false)
    private Double precioExtra;

    @Column(name="obligatorio", nullable = false)
    private Boolean obligatorio;

}
