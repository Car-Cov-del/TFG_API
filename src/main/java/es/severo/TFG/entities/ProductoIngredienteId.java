package es.severo.TFG.entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Embeddable
public class ProductoIngredienteId implements Serializable {

    @Column(name = "productos_id")
    private Long productoId;

    @Column(name = "ingredientes_id")
    private Long ingredienteId;
}