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

    @Column(name="es_editable", nullable = false)
    private Boolean esEditable = true;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne
    @JoinColumn(name = "tamano_id", nullable = false)
    private Tamano tamano;

    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductoIngrediente> ingredientes = new HashSet<>();

    @OneToMany(mappedBy = "producto", cascade = CascadeType.REMOVE, orphanRemoval = true)
    @JsonIgnore
    private Set<PedidoProducto> pedidos = new HashSet<>();

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", precioBase=" + precioBase +
                ", imagen='" + imagen + '\'' +
                ", categoria=" + categoria +
                ", tamano=" + tamano +
                '}';
    }
}
