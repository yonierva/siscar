package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.SolicitudEmpleado;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudEmpleadoRepository extends JpaRepository<SolicitudEmpleado, Integer> {
}