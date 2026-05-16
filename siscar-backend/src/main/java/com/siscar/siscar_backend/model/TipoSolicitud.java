package com.siscar.siscar_backend.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "Tipos_Solicitud")
public class TipoSolicitud {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tipoSolicitud_IDEN")
    private Integer id;

    @Column(name = "codigoFormato")
    private String codigoFormato;

    @Column(name = "nombre")
    private String nombre;
}