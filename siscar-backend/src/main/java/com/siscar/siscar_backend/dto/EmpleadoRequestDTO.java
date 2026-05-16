package com.siscar.siscar_backend.dto;

import lombok.Data;

@Data
public class EmpleadoRequestDTO {

    private String nombres;
    private String apellidos;
    private String tipoIdentificacion;
    private Long numeroIdentificacion;
    private String ciudadExpedicion;
    private String direccionResidencia;
    private Long telefono;
    private String lugarNacimiento;
    private String categoria;
    private String tipoSangre;
    private String urlImagen;
    private Integer idEmpresa;
}