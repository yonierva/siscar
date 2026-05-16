package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Solicitudes_Areas")
public class SolicitudArea {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEN")
    private Integer id;

    @Column(name = "solicitud_IDEN")
    private Integer idSolicitud;

    @Column(name = "area_IDEN")
    private Integer idArea;
}