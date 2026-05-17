package com.siscar.siscar_backend.controller;

import com.siscar.siscar_backend.service.ISolicitudService;
import com.siscar.siscar_backend.dto.SolicitudRequestDTO;
import com.siscar.siscar_backend.dto.StickerResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/solicitud")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class SolicitudController {

    private final ISolicitudService solicitudService;

    @PostMapping("/guardar")
    public ResponseEntity<List<StickerResponseDTO>> guardarSolicitud(@RequestBody SolicitudRequestDTO dto ){
        return ResponseEntity.ok(solicitudService.guardarSolicitud(dto));
    }
};
