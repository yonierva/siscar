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

    @PostMapping("/draft")
    public ResponseEntity<Integer> crearDraft(@RequestBody SolicitudRequestDTO dto) {
        Integer idSolicitud = solicitudService.crearDraft(dto);
        return ResponseEntity.ok(idSolicitud);
    }


    @PostMapping("/{idSolicitud}/guardar")
    public ResponseEntity<List<StickerResponseDTO>> guardarSolicitud(@PathVariable Integer idSolicitud) {
        return ResponseEntity.ok(solicitudService.guardarSolicitud(idSolicitud));
    }

    @PutMapping("/{idSolicitud}")
    public ResponseEntity<String> actualizarDraft(@PathVariable Integer idSolicitud, @RequestBody SolicitudRequestDTO dto) {
        solicitudService.actualizarDraft(idSolicitud, dto);
        return ResponseEntity.ok("Draft actualizado");
    }
};
