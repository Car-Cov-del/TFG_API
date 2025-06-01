package es.severo.TFG.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
@Table(name = "menus")
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @Column(name="precio_base", nullable = false)
    private Double precioBase;

    @Column(name="imagen")
    private String imagen;

    @Column(name="activo", nullable = false)
    private Boolean activo;

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Set<CategoriaMenu> categorias = new HashSet<>();

    @OneToMany(mappedBy = "menu")
    @JsonIgnore
    private Set<MenuPedido> pedidos = new HashSet<>();

}
