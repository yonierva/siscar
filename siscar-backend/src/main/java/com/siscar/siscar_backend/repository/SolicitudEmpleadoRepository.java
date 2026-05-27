package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.SolicitudEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudEmpleadoRepository extends JpaRepository<SolicitudEmpleado, Integer> {
    List<SolicitudEmpleado> findByIdSolicitud(Integer idSolicitud);
    void deleteByIdSolicitud(Integer idSolicitud);
}