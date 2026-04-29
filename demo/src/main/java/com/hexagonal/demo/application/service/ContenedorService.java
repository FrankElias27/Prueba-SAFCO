package com.hexagonal.demo.application.service;

import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.ports.in.ContenedorUseCase;
import com.hexagonal.demo.domain.ports.out.ContenedorPersistencePort;
import com.hexagonal.demo.infraestructure.dto.response.ContenedorResponseDTO;
import com.hexagonal.demo.utils.PageResponse.PageResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class ContenedorService implements ContenedorUseCase {

    private final ContenedorPersistencePort persistencePort;

    @Override
    public Contenedor create(Contenedor domain) {
        log.info("Creando Contenedor");
        return persistencePort.save(domain);
    }

    @Override
    public Contenedor update(Long id, Contenedor domain) {
        log.info("Actualizando Contenedor con id: {}", id);
        Contenedor existente = persistencePort.findById(id);

        Contenedor actualizado = existente.toBuilder()
                .build();

        return persistencePort.save(actualizado);
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando Contenedor con id: {}", id);
        persistencePort.deleteById(id);
    }

    @Override
    public Contenedor findById(Long id) {
        log.info("Buscando Contenedor con id: {}", id);
        return persistencePort.findById(id);
    }

    @Override
    public PageResponse<Contenedor> findAll(int page, int size) {

        log.info("Listando contenedores paginados - page: {}, size: {}", page, size);

        Pageable pageable = PageRequest.of(page, size, Sort.by("fechaCreacion").descending());

        Page<Contenedor> contenedores = persistencePort.findAll(pageable);

        return new PageResponse<>(
                contenedores.getContent(), // 👈 dominio directo
                contenedores.getNumber(),
                contenedores.getSize(),
                contenedores.getTotalElements(),
                contenedores.getTotalPages(),
                contenedores.isFirst(),
                contenedores.isLast()
        );
    }
}
