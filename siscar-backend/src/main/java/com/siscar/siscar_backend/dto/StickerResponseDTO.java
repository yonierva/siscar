package com.siscar.siscar_backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class StickerResponseDTO {

    private Integer id;
    private Integer idSolicitud;
    private Integer idEmpleado;
    private String nombreCompleto;
    private Long numeroIdentificacion;
    private String tipoIdentificacion;
    private String urlImagen;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String horaEntrada;
    private String horaSalida;
    private Boolean esTemporal;
    private List<String> areas;
    private String razonSocialEmpresa;
}