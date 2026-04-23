package com.safco_peru.Prueba.service.Impl;

import com.safco_peru.Prueba.entity.Pallet;
import com.safco_peru.Prueba.mapper.ContenedorMapper;
import com.safco_peru.Prueba.mapper.PalletMapper;
import com.safco_peru.Prueba.repository.ContenedorRepository;
import com.safco_peru.Prueba.repository.PalletRepository;
import com.safco_peru.Prueba.request.PalletRequest;
import com.safco_peru.Prueba.service.PalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PalletServiceImpl implements PalletService {

    private final PalletRepository palletRepository;
    private final PalletMapper palletMapper;

    public Long save(PalletRequest request) {
        Pallet pallet = palletMapper.toPallet(request);
        return palletRepository.save(pallet).getPalletId();
    }

    public void delete(Long id) {
        if (!palletRepository.existsById(id)) {
            throw new RuntimeException("Pallet no encontrado con ID: " + id);
        }
        palletRepository.deleteById(id);
    }
}
