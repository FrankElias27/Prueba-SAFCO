package com.safco_peru.Prueba.repository;


import com.safco_peru.Prueba.entity.Pallet;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;

public interface PalletRepository extends JpaRepository<Pallet,Long> {

    Page<Pallet> findAll(Pageable pageable);
}
