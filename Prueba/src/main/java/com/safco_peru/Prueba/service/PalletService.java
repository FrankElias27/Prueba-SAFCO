package com.safco_peru.Prueba.service;

import com.safco_peru.Prueba.request.ContenedorRequest;
import com.safco_peru.Prueba.request.PalletRequest;

public interface PalletService {
    Long save(PalletRequest request);
    void delete(Long id);
}
