package com.safco_peru.Prueba.request;

import com.safco_peru.Prueba.entity.enums.TipoEspacio;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record ContenedorRequest(

        Long contenedorId,

        @NotEmpty(message = "300")
        String codigoContenedor,

        @NotEmpty(message = "300")
        LocalDateTime fechaCreacion,

        @NotNull(message = "203")
        Long ubicacionId,

        TipoEspacio tipoEspacio

) {}
