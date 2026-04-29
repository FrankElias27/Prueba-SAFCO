package com.hexagonal.demo.infraestructure.mapper;

import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.infraestructure.entity.UbicacionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UbicacionEntityMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "idContenedor", target = "contenedor.id")
    @Mapping(source = "idPallet", target = "pallet.id")
    UbicacionEntity toEntity(Ubicacion domain);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "contenedor.id", target = "idContenedor")
    @Mapping(source = "pallet.id", target = "idPallet")
    Ubicacion toDomain(UbicacionEntity entity);

    List<Ubicacion> toDomainList(List<UbicacionEntity> entities);


}
