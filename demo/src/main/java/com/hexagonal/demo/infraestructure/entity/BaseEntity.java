package com.hexagonal.demo.infraestructure.entity;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;


@Getter
@Setter
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

    @Column(name = "estado",length = 1)
    private String estado;

    @Column(name = "fecha_creacion",updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaCreacion;

    @Column(name = "fecha_modificacion")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechaModificacion;


    @PrePersist
    private void prePersist(){
        this.estado="1";
        this.fechaCreacion=new Date();
    }

    @PreUpdate
    private void preUpdate() {
        this.fechaModificacion=new Date();
    }

}
