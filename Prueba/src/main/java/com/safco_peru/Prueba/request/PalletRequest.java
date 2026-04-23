package com.safco_peru.Prueba.request;

import com.safco_peru.Prueba.entity.Ubicacion;
import jakarta.persistence.*;

import java.time.LocalDateTime;

public record PalletRequest (

        Long palletId,

        String codigoPallet,

        LocalDateTime fechaCreacion,

        Long ubicacionId
){
}
