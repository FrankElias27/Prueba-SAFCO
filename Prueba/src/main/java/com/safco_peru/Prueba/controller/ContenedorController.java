package com.safco_peru.Prueba.controller;

import com.safco_peru.Prueba.request.ContenedorRequest;
import com.safco_peru.Prueba.service.ContenedorService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("contenedor")
@RequiredArgsConstructor
public class ContenedorController {

    private final ContenedorService service;

    @PostMapping
    public ResponseEntity<Long> saveContenedor(
            @Valid @RequestBody ContenedorRequest request
    ) {
        return ResponseEntity.ok(service.save(request));
    }

    @DeleteMapping("/{id}")
    public void deleteContenedor(@PathVariable Long id) {
        service.delete(id);
    }
}
