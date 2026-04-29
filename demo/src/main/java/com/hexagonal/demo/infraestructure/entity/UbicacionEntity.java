package com.hexagonal.demo.infraestructure.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.hexagonal.demo.infraestructure.entity.enums.TipoEspacio;
import lombok.*;

import jakarta.persistence.*;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "ubicaciones", schema = "base_datos")
public class UbicacionEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ubicacion")
    private Long id;

    private Integer numeroAsiento;

    private TipoEspacio tipoEspacio;

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_contenedor")
    private ContenedorEntity contenedor;

    @JsonBackReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_pallet")
    private PalletEntity pallet;
}
