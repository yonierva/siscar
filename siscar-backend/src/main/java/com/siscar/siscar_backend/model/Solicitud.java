package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Solicitudes")
public class Solicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solicitud_IDEN")
    private Integer id;

    @Column(name = "fechaSolicitud")
    private LocalDate fechaSolicitud;

    @Column(name = "tiempoSolicitado")
    private String tiempoSolicitado;

    @Column(name = "nombreJefeInmediato")
    private String nombreJefeInmediato;

    @Column(name = "IDEN_empresa")
    private Integer idEmpresa;

    @Column(name = "IDEN_tipoSolicitud")
    private Integer idTipoSolicitud;

    @Column(name = "horaEntrada")
    private String horaEntrada;

    @Column(name = "horaSalida")
    private String horaSalida;

    @Column(name = "correoFacturacion")
    private String correoFacturacion;

    @Column(name = "actividadRealizar")
    private String actividadRealizar;

    @Column(name = "solicitadoPor")
    private String solicitadoPor;

    @Column(name = "imprimioSolicitud")
    private Boolean imprimioSolicitud;

    @Column(name = "IDEN_area")
    private Integer idArea;

    @Column(name = "estado")
    private String estado;
}