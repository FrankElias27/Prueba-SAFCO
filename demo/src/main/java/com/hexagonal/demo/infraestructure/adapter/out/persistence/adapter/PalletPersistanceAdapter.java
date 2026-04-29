package com.hexagonal.demo.infraestructure.adapter.out.persistence.adapter;

import com.hexagonal.demo.domain.exception.NotFoundException;
import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.models.Pallet;
import com.hexagonal.demo.domain.ports.out.PalletPersistencePort;
import com.hexagonal.demo.infraestructure.adapter.out.persistence.repository.PalletRepository;
import com.hexagonal.demo.infraestructure.entity.PalletEntity;
import com.hexagonal.demo.infraestructure.mapper.PalletEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class PalletPersistanceAdapter implements PalletPersistencePort {

    private final PalletRepository repository;
    private final PalletEntityMapper mapper;

    @Override
    public Pallet save(Pallet domain) {
        PalletEntity entity = mapper.toEntity(domain);

        if (entity.getId() == null) {
            // Lógica para nuevos registros
            entity.setEstado("1");
        } else {
            // Lógica para actualizaciones: preservar metadatos
            PalletEntity existente = repository.findById(domain.getId())
                    .orElseThrow(() -> new NotFoundException("Pallet no encontrado"));

            entity.setEstado(existente.getEstado());
            entity.setFechaCreacion(existente.getFechaCreacion());
        }

        try {
            PalletEntity saved = repository.save(entity);
            return mapper.toDomain(saved);
        } catch (Exception e) {
            log.error("ERROR REAL al guardar Pallet", e);
            throw e;
        }
    }

    @Override
    public Pallet findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NotFoundException("Pallet no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        PalletEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Pallet no encontrado con id: " + id));

        // Eliminación lógica
        entity.setEstado("0");
        repository.save(entity);
    }

    @Override
    public Page<Pallet> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }
}
