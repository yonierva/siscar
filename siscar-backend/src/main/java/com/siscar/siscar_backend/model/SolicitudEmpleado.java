package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "SolicitudEmpleado")
public class SolicitudEmpleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IDEN")
    private Integer id;

    @Column(name = "empleado_IDEN")
    private Integer idEmpleado;

    @Column(name = "solicitud_IDEN")
    private Integer idSolicitud;
}