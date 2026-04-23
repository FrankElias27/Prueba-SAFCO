package com.safco_peru.Prueba.service.Impl;

import com.safco_peru.Prueba.entity.Contenedor;
import com.safco_peru.Prueba.mapper.ContenedorMapper;
import com.safco_peru.Prueba.repository.ContenedorRepository;
import com.safco_peru.Prueba.request.ContenedorRequest;
import com.safco_peru.Prueba.service.ContenedorService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContenedorServiceImpl implements ContenedorService {
    private final ContenedorRepository contenedorRepository;
    private final ContenedorMapper contenedorMapper;

    public Long save(ContenedorRequest request) {
        Contenedor contenedor = contenedorMapper.toContenedor(request);
        return contenedorRepository.save(contenedor).getContenedorId();
    }

    public void delete(Long id) {
        if (!contenedorRepository.existsById(id)) {
            throw new RuntimeException("Contenedor no encontrado con ID: " + id);
        }
        contenedorRepository.deleteById(id);
    }
}
