package com.hexagonal.demo.infraestructure.dto.request;

import jakarta.validation.constraints.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ContenedorRequestDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(max = 500, message = "El codigo no puede exceder los 500 caracteres")
    private String codigoContenedor;

}
