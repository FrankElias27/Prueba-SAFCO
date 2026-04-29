package com.hexagonal.demo.infraestructure.mapper;

import com.hexagonal.demo.domain.models.Pallet;
import com.hexagonal.demo.infraestructure.dto.request.PalletRequestDTO;
import com.hexagonal.demo.infraestructure.dto.response.PalletResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PalletWebMapper {

    Pallet toDomain(PalletRequestDTO requestDto);

    PalletResponseDTO toResponseDto(Pallet domain);

    List<PalletResponseDTO> toResponseDtoList(List<Pallet> domains);
}
