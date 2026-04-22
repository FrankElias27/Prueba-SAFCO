package com.safco_peru.Prueba.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "contenedor")
public class Contenedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long contenedorId;

    @Column(unique = true, nullable = false)
    private String codigoContenedor;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @OneToMany(mappedBy = "contenedor",fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JsonIgnore
    private Set<UbicacionContenedor> ubicaciones  = new HashSet<>();

    @PrePersist
    protected void onCreate() {
        fechaCreacion = LocalDateTime.now();
    }


}
