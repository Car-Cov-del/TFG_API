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
@Table(name = "productos")
public class Producto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="precio_base", nullable = false)
    private Double precioBase;

    @Column(name="imagen")
    private String imagen;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "tamano_id", nullable = false)
    private Tamano tamano;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductoIngrediente> ingredientes = new HashSet<>();

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<PedidoProducto> pedidos = new HashSet<>();

    @OneToMany(mappedBy = "producto")
    @JsonIgnore
    private Set<EspecificacionMenu> pedidosMenu = new HashSet<>();
}
