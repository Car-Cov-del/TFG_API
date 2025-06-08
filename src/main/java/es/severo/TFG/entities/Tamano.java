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
@Table(name = "tamanos")
public class Tamano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="nombre", nullable = false)
    private String nombre;

    @OneToMany(mappedBy = "tamano")
    @JsonIgnore
    private Set<Producto> productos = new HashSet<>();

    @Override
    public String toString() {
        return "Tamano{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
