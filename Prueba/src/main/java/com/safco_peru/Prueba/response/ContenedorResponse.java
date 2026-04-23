package com.safco_peru.Prueba.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.safco_peru.Prueba.entity.Ubicacion;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ContenedorResponse {

    private Long contenedorId;
    private String codigoContenedor;
    private LocalDateTime fechaCreacion;

}
