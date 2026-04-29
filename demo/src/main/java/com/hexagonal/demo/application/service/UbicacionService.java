package com.hexagonal.demo.application.service;

import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.domain.ports.in.UbicacionUseCase;
import com.hexagonal.demo.domain.ports.out.UbicacionPersistencePort;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class UbicacionService implements UbicacionUseCase {

    private final UbicacionPersistencePort persistencePort;

    @Override
    public Ubicacion create(Ubicacion domain) {
        log.info("Creando Ubicacion");
        return persistencePort.save(domain);
    }

    @Override
    public Ubicacion update(Long id, Ubicacion domain) {
        log.info("Actualizando Ubicacion con id: {}", id);
        Ubicacion existente = persistencePort.findById(id);

        Ubicacion actualizado = existente.toBuilder()
                .build();

        return persistencePort.save(actualizado);
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando Ubicacion con id: {}", id);
        persistencePort.deleteById(id);
    }

    @Override
    public Ubicacion findById(Long id) {
        log.info("Buscando Ubicacion con id: {}", id);
        return persistencePort.findById(id);
    }

    @Override
    public List<Ubicacion> findByContenedorId(Long contenedorId) {
        return persistencePort.findByContenedorId(contenedorId);
    }
}
