package com.hexagonal.demo.infraestructure.dto.response;

import com.hexagonal.demo.infraestructure.entity.enums.TipoEspacio;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PalletResponseDTO {

    private Long id;
    private String codigoPallet;
}
