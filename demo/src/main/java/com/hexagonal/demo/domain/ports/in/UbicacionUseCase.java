package com.hexagonal.demo.domain.ports.in;

import com.hexagonal.demo.domain.models.Ubicacion;

import java.util.List;

public interface UbicacionUseCase {

    Ubicacion create(Ubicacion domain);

    Ubicacion update(Long id, Ubicacion domain);

    void delete(Long id);

    Ubicacion findById(Long id);

    List<Ubicacion> findByContenedorId(Long contenedorId);
}
