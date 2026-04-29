package com.hexagonal.demo.infraestructure.adapter.out.persistence.adapter;

import com.hexagonal.demo.domain.exception.NotFoundException;
import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.domain.ports.out.ContenedorPersistencePort;
import com.hexagonal.demo.infraestructure.adapter.out.persistence.repository.ContenedorRepository;
import com.hexagonal.demo.infraestructure.entity.ContenedorEntity;
import com.hexagonal.demo.infraestructure.mapper.ContenedorEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class ContenedorPersistenceAdapter implements ContenedorPersistencePort {

    private final ContenedorRepository repository;
    private final ContenedorEntityMapper mapper;

    @Override
    public Contenedor save(Contenedor domain) {
        ContenedorEntity entity = mapper.toEntity(domain);
        if (entity.getId() == null) {
            entity.setEstado("1");
        } else {
            ContenedorEntity existente = repository.findById(domain.getId())
                    .orElseThrow(() -> new NotFoundException("Contenedor no encontrado"));
            entity.setEstado(existente.getEstado());
            entity.setFechaCreacion(existente.getFechaCreacion());
        }
        ContenedorEntity saved = new ContenedorEntity();
        try {
            saved = repository.save(entity);
            return mapper.toDomain(saved);
        } catch (Exception e) {
            log.error("ERROR REAL", e);
            throw e;
        }
    }

    @Override
    public Contenedor findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NotFoundException("Contenedor no encontrado con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        ContenedorEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Contenedor no encontrado con id: " + id));
        entity.setEstado("0");
        repository.save(entity);
    }

    @Override
    public Page<Contenedor> findAll(Pageable pageable) {
        return repository.findAll(pageable)
                .map(mapper::toDomain);
    }


}
