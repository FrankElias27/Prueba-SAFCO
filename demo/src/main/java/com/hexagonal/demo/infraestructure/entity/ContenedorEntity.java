package com.hexagonal.demo.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "contenedores", schema = "base_datos")
public class ContenedorEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_contenedor")
    private Long id;

    private String codigoContenedor;

    @JsonManagedReference
    @OneToMany(mappedBy = "contenedor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UbicacionEntity> ubicaciones;

}
