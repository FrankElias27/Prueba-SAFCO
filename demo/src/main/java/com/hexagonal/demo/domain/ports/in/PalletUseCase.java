package com.hexagonal.demo.domain.ports.in;

import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.models.Pallet;
import com.hexagonal.demo.utils.PageResponse.PageResponse;

public interface PalletUseCase {

    Pallet create(Pallet domain);

    Pallet update(Long id, Pallet domain);

    void delete(Long id);

    Pallet findById(Long id);

    PageResponse<Pallet> findAll(int page, int size);
}
