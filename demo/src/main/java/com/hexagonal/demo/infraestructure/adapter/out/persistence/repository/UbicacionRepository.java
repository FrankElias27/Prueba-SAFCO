package com.hexagonal.demo.infraestructure.adapter.out.persistence.repository;

import com.hexagonal.demo.infraestructure.entity.UbicacionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UbicacionRepository extends JpaRepository<UbicacionEntity, Long> {
    List<UbicacionEntity> findByContenedor_Id(Long contenedorId);
}
