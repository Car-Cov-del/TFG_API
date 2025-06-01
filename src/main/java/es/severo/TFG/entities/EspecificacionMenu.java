package es.severo.TFG.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "especificacion_menu")
public class EspecificacionMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "producto_id", nullable = false)
    @JsonIgnore
    private Producto producto;

    @ManyToOne
    @JoinColumn(name = "menu_pedido_id", nullable = false)
    @JsonIgnore
    private MenuPedido pedidoMenu;

    @ManyToOne
    @JoinColumn(name = "categoria_menu_id", nullable = false)
    @JsonIgnore
    private CategoriaMenu categoriaMenu;

    @Column(name="cantidad", nullable = false)
    private Integer cantidad;

    @OneToMany(mappedBy = "pedidoMenuProducto")
    @JsonIgnore
    private Set<Especificacion> especificaciones = new HashSet<>();
}
