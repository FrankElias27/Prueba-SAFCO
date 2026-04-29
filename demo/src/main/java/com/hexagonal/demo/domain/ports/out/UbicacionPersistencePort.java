package com.hexagonal.demo.domain.ports.out;

import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.infraestructure.entity.UbicacionEntity;

import java.util.List;

public interface UbicacionPersistencePort {
    Ubicacion save(Ubicacion domain);

    Ubicacion findById(Long id);

    void deleteById(Long id);

    List<Ubicacion> findByContenedorId(Long contenedorId);

}
