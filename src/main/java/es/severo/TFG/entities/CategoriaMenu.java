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
@Table(name = "categoria_menu")
public class CategoriaMenu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    @ManyToOne
    @JoinColumn(name = "menu_id", nullable = false)
    @JsonIgnore
    private Menu menu;

    @ManyToOne
    @JoinColumn(name = "categoria_id", nullable = false)
    @JsonIgnore
    private Categoria categoria;

    @Column(name="nombre_seccion", nullable = false)
    private String nombreSeccion;

    @Column(name="cantidad", nullable = false)
    private Integer cantidad;

    @Column(name="obligatorio", nullable = false)
    private Boolean obligatorio;

    @OneToMany(mappedBy = "categoriaMenu")
    @JsonIgnore
    private Set<EspecificacionMenu> productos = new HashSet<>();
}
