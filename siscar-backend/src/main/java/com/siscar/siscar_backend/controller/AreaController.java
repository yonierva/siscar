package com.siscar.siscar_backend.controller;

import com.siscar.siscar_backend.model.Area;
import com.siscar.siscar_backend.service.IAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/areas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class AreaController {

    private final IAreaService areaService;

    @GetMapping
    public ResponseEntity<List<Area>> listarTodas(){
        return ResponseEntity.ok(areaService.listarTodas());
    }
}