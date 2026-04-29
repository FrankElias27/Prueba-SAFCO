package com.hexagonal.demo.infraestructure.adapter.out.persistence.repository;

import com.hexagonal.demo.infraestructure.entity.ContenedorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContenedorRepository extends JpaRepository<ContenedorEntity, Long> {
}
