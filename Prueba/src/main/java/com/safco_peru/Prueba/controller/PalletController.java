package com.safco_peru.Prueba.controller;

import com.safco_peru.Prueba.request.PalletRequest;
import com.safco_peru.Prueba.service.PalletService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pallet")
@RequiredArgsConstructor
public class PalletController {

    private final PalletService service;

    @PostMapping
    public ResponseEntity<Long> savePallet(
            @Valid @RequestBody PalletRequest request
    ) {
        return ResponseEntity.ok(service.save(request));
    }

    @DeleteMapping("/{id}")
    public void deletePallet(@PathVariable Long id) {
        service.delete(id);
    }
}
