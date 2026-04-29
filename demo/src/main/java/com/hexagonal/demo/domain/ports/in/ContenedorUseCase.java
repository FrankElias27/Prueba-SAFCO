package com.hexagonal.demo.domain.ports.in;

import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.infraestructure.entity.UbicacionEntity;
import com.hexagonal.demo.utils.PageResponse.PageResponse;

import java.util.List;

public interface ContenedorUseCase {

    Contenedor create(Contenedor domain);

    Contenedor update(Long id, Contenedor domain);

    void delete(Long id);

    Contenedor findById(Long id);

    PageResponse<Contenedor> findAll(int page, int size);



}
