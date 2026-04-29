package com.hexagonal.demo.utils.classPersonalized;

import lombok.Builder;
import lombok.Value;

import java.util.Collection;

@Value
@Builder
public class ApiResponseProvider<T> {

    private String codigo;
    private String respuesta;
    private T data;
    private Integer cantidad;
    private String error;


    // 🔹 SUCCESS
    public static <T> ApiResponseProvider<T> success(T data) {
        return ApiResponseProvider.<T>builder()
                .codigo("1")
                .respuesta("Operación exitosa")
                .data(data)
                .cantidad(
                        data instanceof Collection
                                ? ((Collection<?>) data).size()
                                : null
                )
                .build();
    }

    public static <T> ApiResponseProvider<T> success(T data, String mensaje) {
        return ApiResponseProvider.<T>builder()
                .codigo("1")
                .respuesta(mensaje)
                .data(data)
                .cantidad(
                        data instanceof Collection
                                ? ((Collection<?>) data).size()
                                : null
                )
                .build();
    }

    // 🔹 ERROR
    public static ApiResponseProvider<Void> error(String mensaje, String error) {
        return ApiResponseProvider.<Void>builder()
                .codigo("0")
                .respuesta(mensaje)
                .error(error)
                .build();
    }

}
