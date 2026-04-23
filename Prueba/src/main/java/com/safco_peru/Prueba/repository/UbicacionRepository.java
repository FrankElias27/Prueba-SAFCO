package com.safco_peru.Prueba.repository;


import com.safco_peru.Prueba.entity.Ubicacion;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface UbicacionRepository extends JpaRepository<Ubicacion,Long> {
    Page<Ubicacion> findAll(Pageable pageable);
}
