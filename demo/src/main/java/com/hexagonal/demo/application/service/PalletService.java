package com.hexagonal.demo.application.service;

import com.hexagonal.demo.domain.models.Pallet;
import com.hexagonal.demo.domain.ports.in.PalletUseCase;
import com.hexagonal.demo.domain.ports.out.PalletPersistencePort;
import com.hexagonal.demo.utils.PageResponse.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class PalletService implements PalletUseCase {

    private final PalletPersistencePort persistencePort;

    @Override
    public Pallet create(Pallet domain) {
        log.info("Creando Pallet");
        return persistencePort.save(domain);
    }

    @Override
    public Pallet update(Long id, Pallet domain) {
        log.info("Actualizando Pallet con id: {}", id);
        Pallet existente = persistencePort.findById(id);

        Pallet actualizado = existente.toBuilder()
                .build();

        return persistencePort.save(actualizado);
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando Pallet con id: {}", id);
        persistencePort.deleteById(id);
    }

    @Override
    public Pallet findById(Long id) {
        log.info("Buscando Pallet con id: {}", id);
        return persistencePort.findById(id);
    }

    @Override
    public PageResponse<Pallet> findAll(int page, int size) {

        log.info("Listando pallets paginados - page: {}, size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaCreacion").descending());

        Page<Pallet> pallets = persistencePort.findAll(pageable);

        return new PageResponse<>(
                pallets.getContent(), // 👈 dominio directo
                pallets.getNumber(),
                pallets.getSize(),
                pallets.getTotalElements(),
                pallets.getTotalPages(),
                pallets.isFirst(),
                pallets.isLast()
        );
    }
}
