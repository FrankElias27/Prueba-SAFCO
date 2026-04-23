package com.safco_peru.Prueba.entity;

import com.safco_peru.Prueba.entity.enums.TipoEspacio;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@SuperBuilder
@Table(name = "ubicacion")
public class Ubicacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long ubicacionId;

    private Integer numeroAsiento; // 1-24

    @Enumerated(EnumType.STRING)
    private TipoEspacio tipo; // PALLET o SENSOR

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "contenedor_id", nullable = true)
    private Contenedor contenedor;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pallet_id", nullable = true)
    private Pallet pallet;

    public boolean estaDisponible() {
        return this.tipo == TipoEspacio.PALLET && this.pallet == null;
    }
}
