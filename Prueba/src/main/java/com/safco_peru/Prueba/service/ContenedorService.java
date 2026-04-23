package com.safco_peru.Prueba.service;

import com.safco_peru.Prueba.request.ContenedorRequest;

public interface ContenedorService {
    Long save(ContenedorRequest request);
    void delete(Long id);
}
