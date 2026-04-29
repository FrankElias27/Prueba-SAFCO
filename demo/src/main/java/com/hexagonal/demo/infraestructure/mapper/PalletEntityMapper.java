package com.hexagonal.demo.infraestructure.mapper;

import com.hexagonal.demo.domain.models.Pallet;
import com.hexagonal.demo.infraestructure.entity.PalletEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PalletEntityMapper {

    @Mapping(source = "id", target = "id")
    PalletEntity toEntity(Pallet domain);

    @Mapping(source = "id", target = "id")
    Pallet toDomain(PalletEntity entity);

    List<Pallet> toDomainList(List<PalletEntity> entities);
}
