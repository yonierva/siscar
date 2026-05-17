package com.siscar.siscar_backend.controller;

import com.siscar.siscar_backend.dto.StickerResponseDTO;
import com.siscar.siscar_backend.service.IStickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/stickers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class StickerController {

    private final IStickerService stickerService;

    @GetMapping
    public ResponseEntity<List<StickerResponseDTO>> listarPorSolicitud(@RequestParam Integer idSolicitud) {
        return ResponseEntity.ok(stickerService.listarPorSolicitud(idSolicitud));
    }
}