package com.hexagonal.demo.domain.ports.out;

import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.models.Pallet;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PalletPersistencePort {

    Pallet save(Pallet domain);
    Pallet findById(Long id);
    void deleteById(Long id);
    Page<Pallet> findAll(Pageable pageable);
}
