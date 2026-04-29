package com.hexagonal.demo.domain.models;


import lombok.Builder;
import lombok.Value;

import java.time.LocalDateTime;

@Value
@Builder(toBuilder = true)
public class Contenedor {

    private Long id;
    private String codigoContenedor;

}
