package es.severo.TFG.entities;

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
@Table(name = "restaurantes")
public class Restaurante {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "calle", nullable = false)
    private String calle;

    @Column(name = "telefono", nullable = false)
    private String telefono;

    @Column(name = "cif", nullable = false, unique = true)
    private String cif;

    @Column(name = "email")
    private String email;

    @Column(name = "web")
    private String web;

    @OneToMany(mappedBy = "restaurante", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Usuario> usuarios = new HashSet<>();

    @OneToMany(mappedBy = "restaurante")
    private Set<Pedido> pedidos = new HashSet<>();
}
