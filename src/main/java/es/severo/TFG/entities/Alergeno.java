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
@Table(name = "alergenos")
public class Alergeno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="descripcion", nullable = false)
    private String descripcion;

    @Column(name="imagen")
    private String imagen;

    @ManyToMany(mappedBy = "alergenos")
    @JsonIgnore
    private Set<Ingrediente> ingredientes = new HashSet<>();

    public Alergeno(String descripcion, String url, String nombre) {
        this.descripcion = descripcion;
        this.imagen = url;
        this.nombre = nombre;
    }
}
