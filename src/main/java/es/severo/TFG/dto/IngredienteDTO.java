package es.severo.TFG.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class IngredienteDTO {
    private String nombre;
    private String imagen;
    private Set<Long> alergenoIds;
}
