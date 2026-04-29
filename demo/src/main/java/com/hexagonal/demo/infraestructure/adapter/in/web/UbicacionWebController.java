package com.hexagonal.demo.infraestructure.adapter.in.web;

import com.hexagonal.demo.domain.models.Ubicacion;
import com.hexagonal.demo.domain.ports.in.UbicacionUseCase;
import com.hexagonal.demo.infraestructure.dto.request.UbicacionRequestDTO;
import com.hexagonal.demo.infraestructure.dto.response.UbicacionResponseDTO;
import com.hexagonal.demo.infraestructure.mapper.UbicacionWebMapper;
import com.hexagonal.demo.utils.classPersonalized.ApiResponseProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ubicacion")
@RequiredArgsConstructor
public class UbicacionWebController {

    private final UbicacionUseCase useCase;
    private final UbicacionWebMapper mapper;

    @PostMapping
    public ResponseEntity<ApiResponseProvider<UbicacionResponseDTO>> create(@Valid @RequestBody UbicacionRequestDTO requestDTO) {
        Ubicacion domain = mapper.toDomain(requestDTO);
        System.out.println(">>> DATOS EN DOMINIO: " + domain.toString());
        Ubicacion created = useCase.create(domain);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ApiResponseProvider.success(mapper.toResponseDto(created), "Ubicación creada de manera exitosa"));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<UbicacionResponseDTO>> update(
            @PathVariable Long id,
            @Valid @RequestBody UbicacionRequestDTO requestDTO) {
        Ubicacion domain = mapper.toDomain(requestDTO);
        Ubicacion updated = useCase.update(id, domain);
        return ResponseEntity.ok(
                ApiResponseProvider.success(mapper.toResponseDto(updated), "Ubicación modificada de manera exitosa"));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<UbicacionResponseDTO>> findById(@PathVariable Long id) {
        Ubicacion found = useCase.findById(id);
        return ResponseEntity.ok(
                ApiResponseProvider.success(mapper.toResponseDto(found), "Ubicación obtenida exitosamente"));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponseProvider<Void>> delete(@PathVariable Long id) {
        useCase.delete(id);
        return ResponseEntity.ok(
                ApiResponseProvider.success(null, "Ubicación desactivada con éxito"));
    }

    @GetMapping("/{contenedorId}/ubicaciones")
    public ResponseEntity<ApiResponseProvider<List<UbicacionResponseDTO>>> findByContenedor(
            @PathVariable Long contenedorId) {

        List<Ubicacion> ubicaciones = useCase.findByContenedorId(contenedorId);

        List<UbicacionResponseDTO> dtoList = ubicaciones.stream()
                .map(mapper::toResponseDto)
                .toList();

        return ResponseEntity.ok(
                ApiResponseProvider.success(dtoList, "Ubicaciones del contenedor obtenidas exitosamente")
        );
    }
}
