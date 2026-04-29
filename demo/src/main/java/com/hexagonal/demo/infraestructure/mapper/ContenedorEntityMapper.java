package com.hexagonal.demo.infraestructure.mapper;
import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.infraestructure.entity.ContenedorEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ContenedorEntityMapper {
    @Mapping(source = "id", target = "id")
    ContenedorEntity toEntity(Contenedor domain);

    @Mapping(source = "id", target = "id")
    Contenedor toDomain(ContenedorEntity entity);

    List<Contenedor> toDomainList(List<ContenedorEntity> entities);
}
