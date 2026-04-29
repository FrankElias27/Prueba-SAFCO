package com.hexagonal.demo.infraestructure.mapper;

import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.infraestructure.dto.request.ContenedorRequestDTO;
import com.hexagonal.demo.infraestructure.dto.response.ContenedorResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContenedorWebMapper {

    Contenedor toDomain(ContenedorRequestDTO requestDto);

    ContenedorResponseDTO toResponseDto(Contenedor domain);

    List<ContenedorResponseDTO> toResponseDtoList(List<Contenedor> domains);
}
