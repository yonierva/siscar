package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.Area;
import com.siscar.siscar_backend.model.SolicitudArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SolicitudAreaRepository extends JpaRepository<SolicitudArea, Integer> {
    List<Integer> findAreaIdsBySolicitudId(@Param("idSolicitud") Integer idSolicitud);
}