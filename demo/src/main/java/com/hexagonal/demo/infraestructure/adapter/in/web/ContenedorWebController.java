package com.hexagonal.demo.infraestructure.adapter.in.web;
import com.hexagonal.demo.domain.models.Contenedor;
import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.domain.ports.in.ContenedorUseCase;
import com.hexagonal.demo.infraestructure.dto.request.ContenedorRequestDTO;
import com.hexagonal.demo.infraestructure.dto.response.ContenedorResponseDTO;
import com.hexagonal.demo.infraestructure.dto.response.UbicacionResponseDTO;
import com.hexagonal.demo.infraestructure.mapper.ContenedorWebMapper;
import com.hexagonal.demo.utils.PageResponse.PageResponse;
import com.hexagonal.demo.utils.classPersonalized.ApiResponseProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import java.util.List;

@RestController
@RequestMapping("/contenedor")
@RequiredArgsConstructor
public class ContenedorWebController {

    private final ContenedorUseCase useCase;
    private final ContenedorWebMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponseProvider<ContenedorResponseDTO>> create(@Valid @RequestBody ContenedorRequestDTO requestDTO) {
        Contenedor domain = mapper.toDomain(requestDTO);
        Contenedor created = useCase.create(domain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseProvider.success(mapper.toResponseDto(created), "Contenedor creado de manera exitosa"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<ContenedorResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody ContenedorRequestDTO requestDTO) {
        Contenedor domain = mapper.toDomain(requestDTO);
        Contenedor updated = useCase.update(id, domain);
        return ResponseEntity.ok(
                ApiResponseProvider.success(mapper.toResponseDto(updated), "Contenedor modificado de manera exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<ContenedorResponseDTO>> findById(@PathVariable Long id) {
        Contenedor found = useCase.findById(id);
        return ResponseEntity.ok(
                ApiResponseProvider.success(mapper.toResponseDto(found), "Contenedor obtenido exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<Void>> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.ok(
                ApiResponseProvider.success(null, "Contenedor desactivado con éxito"));
    }

    @GetMapping
    public ResponseEntity<ApiResponseProvider<PageResponse<ContenedorResponseDTO>>> findAll(
            @RequestParam(name = "page", defaultValue = "0", required = false) int page,
            @RequestParam(name = "size", defaultValue = "10", required = false) int size
    ) {

        PageResponse<Contenedor> response = useCase.findAll(page, size);

        PageResponse<ContenedorResponseDTO> dtoResponse = new PageResponse<>(
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
                ApiResponseProvider.success(dtoResponse, "Contenedores obtenidos exitosamente")
        );
    }


}
