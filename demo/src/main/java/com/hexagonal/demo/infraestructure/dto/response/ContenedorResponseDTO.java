package com.hexagonal.demo.infraestructure.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorResponseDTO {

    private Long id;
    private String codigoContenedor;
    private Boolean estado;
}
