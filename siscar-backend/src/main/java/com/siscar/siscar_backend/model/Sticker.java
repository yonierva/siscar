package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Entity
@Table(name = "Stickers")
public class Sticker {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sticker_IDEN")
    private Integer id;

    @Column(name = "fechaEntrada")
    private LocalDate fechaEntrada;

    @Column(name = "fechaSalida")
    private LocalDate fechaSalida;

    @Column(name = "horaEntrada")
    private String horaEntrada;

    @Column(name = "horaSalida")
    private String horaSalida;

    @Column(name = "IDEN_empleado")
    private Integer idEmpleado;

    @Column(name = "IDEN_solicitud")
    private Integer idSolicitud;

    @Column(name = "devolvioStickers")
    private Boolean devolvioStickers;

    @Column(name = "EsTemporal")
    private Boolean esTemporal;

    @Column(name = "fechaDevolucion")
    private LocalDate fechaDevolucion;
}