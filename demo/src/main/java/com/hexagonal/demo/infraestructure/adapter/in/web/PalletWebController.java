package com.hexagonal.demo.infraestructure.adapter.in.web;
import com.hexagonal.demo.domain.models.Pallet;
import com.hexagonal.demo.domain.ports.in.PalletUseCase;
import com.hexagonal.demo.infraestructure.dto.request.PalletRequestDTO;
import com.hexagonal.demo.infraestructure.dto.response.PalletResponseDTO;
import com.hexagonal.demo.infraestructure.mapper.PalletWebMapper;
import com.hexagonal.demo.utils.PageResponse.PageResponse;
import com.hexagonal.demo.utils.classPersonalized.ApiResponseProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pallet")
@RequiredArgsConstructor
public class PalletWebController {

    private final PalletUseCase useCase;
    private final PalletWebMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponseProvider<PalletResponseDTO>> create(@Valid @RequestBody PalletRequestDTO requestDTO) {
        Pallet domain = mapper.toDomain(requestDTO);
        Pallet created = useCase.create(domain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseProvider.success(mapper.toResponseDto(created), "Pallet creado de manera exitosa"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<PalletResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody PalletRequestDTO requestDTO) {
        Pallet domain = mapper.toDomain(requestDTO);
        Pallet updated = useCase.update(id, domain);
        return ResponseEntity.ok(
                ApiResponseProvider.success(mapper.toResponseDto(updated), "Pallet modificado de manera exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<PalletResponseDTO>> findById(@PathVariable Long id) {
        Pallet found = useCase.findById(id);
        return ResponseEntity.ok(
                ApiResponseProvider.success(mapper.toResponseDto(found), "Pallet obtenido exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<Void>> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.ok(
                ApiResponseProvider.success(null, "Pallet desactivado con éxito"));
    }

    @GetMapping
    public ResponseEntity<ApiResponseProvider<PageResponse<PalletResponseDTO>>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {

        PageResponse<Pallet> response = useCase.findAll(page, size);

        PageResponse<PalletResponseDTO> dtoResponse = new PageResponse<>(
                response.getContent().stream()
                        .map(mapper::toResponseDto)
                        .toList(),
                response.getNumber(),
                response.getSize(),
                response.getTotalElements(),
                response.getTotalPages(),
                response.isFirst(),
                response.isLast()
        );

        return ResponseEntity.ok(
                ApiResponseProvider.success(dtoResponse, "Pallets obtenidos exitosamente")
        );
    }
}
