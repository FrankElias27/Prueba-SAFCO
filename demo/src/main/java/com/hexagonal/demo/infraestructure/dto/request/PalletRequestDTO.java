package com.hexagonal.demo.infraestructure.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PalletRequestDTO {

    @NotBlank(message = "El codigo es obligatorio")
    @Size(max = 500, message = "El codigo no puede exceder los 500 caracteres")
    private String codigoPallet;

}
