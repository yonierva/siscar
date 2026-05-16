package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Areas")
public class Area {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "area_IDEN")
    private Integer id;

    @Column(name = "nombreArea")
    private String nombreArea;

    @Column(name = "Carnet")
    private String carnet;
}