package com.safco_peru.Prueba.mapper;

import com.safco_peru.Prueba.entity.Pallet;
import com.safco_peru.Prueba.entity.Ubicacion;
import com.safco_peru.Prueba.request.PalletRequest;
import com.safco_peru.Prueba.response.PalletResponse;
import org.springframework.stereotype.Service;

@Service
public class PalletMapper {

    public Pallet toPallet(PalletRequest request) {
        return Pallet.builder()
                .palletId(request.palletId())
                .codigoPallet(request.codigoPallet())
                .fechaCreacion(request.fechaCreacion())
                .ubicacion(Ubicacion.builder()
                        .ubicacionId(request.ubicacionId())
                        .build()
                )
                .build();
    }

    public PalletResponse toPalletResponse(Pallet pallet) {
        return PalletResponse.builder()
                .palletId(pallet.getPalletId())
                .codigoPallet(pallet.getCodigoPallet())
                .fechaCreacion(pallet.getFechaCreacion())
                .build();
    }
}
