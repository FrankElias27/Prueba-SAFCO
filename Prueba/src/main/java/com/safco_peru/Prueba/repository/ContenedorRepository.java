package com.safco_peru.Prueba.repository;

import com.safco_peru.Prueba.entity.Contenedor;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface ContenedorRepository extends JpaRepository<Contenedor,Long> {

    Page<Contenedor> findAll(Pageable pageable);
}
