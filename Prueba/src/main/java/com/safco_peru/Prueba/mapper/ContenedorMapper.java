package com.safco_peru.Prueba.mapper;

import com.safco_peru.Prueba.entity.Contenedor;
import com.safco_peru.Prueba.entity.Ubicacion;
import com.safco_peru.Prueba.request.ContenedorRequest;
import com.safco_peru.Prueba.response.ContenedorResponse;
import org.springframework.stereotype.Service;

@Service
public class ContenedorMapper {
    public Contenedor toContenedor(ContenedorRequest request) {
        return Contenedor.builder()
                .contenedorId(request.contenedorId())
                .codigoContenedor(request.codigoContenedor())
                .fechaCreacion(request.fechaCreacion())
                .build();
    }

    public ContenedorResponse toContenedorResponse(Contenedor contenedor) {
        return ContenedorResponse.builder()
                .contenedorId(contenedor.getContenedorId())
                .codigoContenedor(contenedor.getCodigoContenedor())
                .fechaCreacion(contenedor.getFechaCreacion())
                .build();
    }

}
