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
@Table(name = "ingredientes")
public class Ingrediente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="imagen")
    private String imagen;

    @Column(name="es_anadible", nullable = false)
    private Boolean esAnadible = true;

    @ManyToMany
    @JoinTable(
            name = "ingrediente_alergeno",
            joinColumns = @JoinColumn(name = "ingredientes_id"),
            inverseJoinColumns = @JoinColumn(name = "alergenos_id")
    )
    private Set<Alergeno> alergenos = new HashSet<>();

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<ProductoIngrediente> productos = new HashSet<>();

    @OneToMany(mappedBy = "ingrediente", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private Set<Especificacion> especificaciones = new HashSet<>();

    @Override
    public String toString() {
        return "Ingrediente{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", imagen='" + imagen + '\'' +
                ", alergenos=" + alergenos +
                '}';
    }
}
