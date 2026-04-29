package com.hexagonal.demo.infraestructure.adapter.out.persistence.adapter;

import com.hexagonal.demo.domain.exception.NotFoundException;
import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.domain.ports.out.UbicacionPersistencePort;
import com.hexagonal.demo.infraestructure.adapter.out.persistence.repository.UbicacionRepository;
import com.hexagonal.demo.infraestructure.entity.UbicacionEntity;
import com.hexagonal.demo.infraestructure.mapper.UbicacionEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
@Slf4j
public class UbicacionPersistenceAdapter implements UbicacionPersistencePort {

    private final UbicacionRepository repository;
    private final UbicacionEntityMapper mapper;

    @Override
    public Ubicacion save(Ubicacion domain) {
        UbicacionEntity entity = mapper.toEntity(domain);

        if (entity.getId() == null) {
            // Lógica para nuevos registros
            entity.setEstado("1");
        } else {
            // Lógica para actualizaciones: preservar metadatos
            UbicacionEntity existente = repository.findById(domain.getId())
                    .orElseThrow(() -> new NotFoundException("Ubicación no encontrada"));

            entity.setEstado(existente.getEstado());
            entity.setFechaCreacion(existente.getFechaCreacion());
        }

        try {
            UbicacionEntity saved = repository.save(entity);
            return mapper.toDomain(saved);
        } catch (Exception e) {
            log.error("ERROR REAL al guardar Ubicación", e);
            throw e;
        }
    }

    @Override
    public Ubicacion findById(Long id) {
        return repository.findById(id)
                .map(mapper::toDomain)
                .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id: " + id));
    }

    @Override
    public void deleteById(Long id) {
        UbicacionEntity entity = repository.findById(id)
                .orElseThrow(() -> new NotFoundException("Ubicación no encontrada con id: " + id));

        // Eliminación lógica
        entity.setEstado("0");
        repository.save(entity);
    }

    @Override
    public List<Ubicacion> findByContenedorId(Long contenedorId) {

        return repository.findByContenedor_Id(contenedorId)
                .stream()
                .map(mapper::toDomain)
                .toList();
    }
}
