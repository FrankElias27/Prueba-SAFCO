package com.hexagonal.demo.infraestructure.dto.request;

import com.hexagonal.demo.infraestructure.entity.enums.TipoEspacio;
import jakarta.validation.constraints.Max;
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
public class UbicacionRequestDTO {

    @NotNull(message = "El numero es obligatorio")
    @Max(value = 24, message = "El número de asiento no puede exceder 24")
    private Integer numeroAsiento;


    @NotNull(message = "El tipo de espacio es obligatorio")
    private TipoEspacio tipoEspacio;

    @NotNull(message = "El id del contenedor es obligatorio")
    private Long idContenedor;

    @NotNull(message = "El id del pallet es obligatorio")
    private Long idPallet;
}
