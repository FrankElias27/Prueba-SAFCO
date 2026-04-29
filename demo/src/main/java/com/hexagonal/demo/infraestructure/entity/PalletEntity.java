package com.hexagonal.demo.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "pallets", schema = "base_datos")
public class PalletEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_pallet")
    private Long id;

    private String codigoPallet;

    @JsonManagedReference
    @OneToOne(mappedBy = "pallet", cascade = CascadeType.ALL, orphanRemoval = true)
    private UbicacionEntity ubicacion;
}
