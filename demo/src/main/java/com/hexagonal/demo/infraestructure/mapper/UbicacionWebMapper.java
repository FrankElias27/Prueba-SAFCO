package com.hexagonal.demo.infraestructure.mapper;

import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.infraestructure.dto.request.UbicacionRequestDTO;
import com.hexagonal.demo.infraestructure.dto.response.UbicacionResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UbicacionWebMapper {

    Ubicacion toDomain(UbicacionRequestDTO requestDto);

    UbicacionResponseDTO toResponseDto(Ubicacion domain);

    List<UbicacionResponseDTO> toResponseDtoList(List<Ubicacion> domains);
}
