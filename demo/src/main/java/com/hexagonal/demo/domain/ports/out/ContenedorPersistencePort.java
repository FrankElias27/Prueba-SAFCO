package com.hexagonal.demo.domain.ports.out;

import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.infraestructure.entity.UbicacionEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ContenedorPersistencePort{

    Contenedor save(Contenedor domain);

    Contenedor findById(Long id);

    void deleteById(Long id);

    Page<Contenedor> findAll(Pageable pageable);


}
