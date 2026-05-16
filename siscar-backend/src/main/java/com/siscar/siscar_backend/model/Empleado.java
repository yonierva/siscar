package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Empleados")

public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empleado_IDEN")
    private Integer id;

    @Column(name = "nombres")
    private String nombres;

    @Column(name = "apellidos")
    private String apellidos;

    @Column(name = "nombreCompleto")
    private String nombreCompleto;

    @Column(name = "tipoIdentificacion")
    private String tipoIdentificacion;

    @Column(name = "numeroIdentificacion", unique = true)
    private Long numeroIdentificacion;

    @Column(name = "ciudadExpedicion")
    private String ciudadExpedicion;

    @Column(name = "direccionResidencia")
    private String direccionResidencia;

    @Column(name = "telefono")
    private Long telefono;

    @Column(name = "fechaNacimiento")
    private java.time.LocalDate fechaNacimiento;

    @Column(name = "lugarNacimiento")
    private String lugarNacimiento;

    @Column(name = "tipoContrato")
    private Integer tipoContrato;

    @Column(name = "licenciaConduccion")
    private String licenciaConduccion;

    @Column(name = "categoria")
    private String categoria;

    @Column(name = "TipoSangre")
    private String tipoSangre;

    @Column(name = "UrlImagen")
    private String urlImagen;

    @Column(name = "IDEN_empresa")
    private Integer idEmpresa;
}