package com.siscar.siscar_backend.dto;

import lombok.Data;

@Data
public class EmpleadoResponseDTO {

    private Integer id;
    private String nombres;
    private String apellidos;
    private String nombreCompleto;
    private String tipoIdentificacion;
    private Long numeroIdentificacion;
    private String tipoSangre;
    private String urlImagen;
    private String categoria;
}