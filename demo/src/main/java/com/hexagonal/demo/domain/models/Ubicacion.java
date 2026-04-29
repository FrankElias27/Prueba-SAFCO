package com.hexagonal.demo.domain.models;

import com.hexagonal.demo.domain.models.enums.TipoEspacio;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Value;

@Value
@Builder(toBuilder = true)
public class Ubicacion {

    private Long id;
    private Integer numeroAsiento;
    private TipoEspacio tipoEspacio;
    private Long idContenedor;
    private Long idPallet;

}
