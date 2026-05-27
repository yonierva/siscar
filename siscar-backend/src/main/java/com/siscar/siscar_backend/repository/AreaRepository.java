package com.siscar.siscar_backend.repository;

import com.siscar.siscar_backend.model.Area;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AreaRepository extends JpaRepository<Area, Integer> {
}