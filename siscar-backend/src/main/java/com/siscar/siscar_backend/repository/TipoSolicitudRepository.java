package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.TipoSolicitud;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoSolicitudRepository extends JpaRepository<TipoSolicitud, Integer> {
}