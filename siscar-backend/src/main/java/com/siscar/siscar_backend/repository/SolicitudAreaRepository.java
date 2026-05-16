package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.SolicitudArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudAreaRepository extends JpaRepository<SolicitudArea, Integer> {
}