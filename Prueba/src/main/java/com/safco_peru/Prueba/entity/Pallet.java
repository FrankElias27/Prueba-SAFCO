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
@Table(name = "pallet")
public class Pallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long palletId;

    @Column(unique = true, nullable = false)
    private String codigoPallet;

    @Column(name = "fecha_creacion", updatable = false)
    private LocalDateTime fechaCreacion;

    @OneToOne(mappedBy = "pallet",fetch = FetchType.LAZY)
    private UbicacionContenedor ubicacionContenedor;

    @PrePersist
    protected void onCreate() {
        this.fechaCreacion = LocalDateTime.now();
    }
}
