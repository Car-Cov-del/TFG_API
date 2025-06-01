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
@Table(name = "categorias")
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="imagen")
    private String imagen;

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    @OneToMany(mappedBy = "categoria")
    @JsonIgnore
    private Set<CategoriaMenu> menus = new HashSet<>();
}
