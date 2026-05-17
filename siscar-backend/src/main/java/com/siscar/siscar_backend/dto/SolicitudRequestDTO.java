package com.siscar.siscar_backend.dto;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class SolicitudRequestDTO {

    private Integer idEmpresa;
    private LocalDate fechaEntrada;
    private LocalDate fechaSalida;
    private String horaEntrada;
    private String horaSalida;
    private Boolean esTemporal;
    private Integer idTipoSolicitud;
    private List<Integer> areasSeleccionadas;
    private List<Integer> empleadosSeleccionados;
    private String nombreJefeInmediato;
    private String actividadRealizar;
    private String correoFacturacion;
    private String solicitadoPor;
}