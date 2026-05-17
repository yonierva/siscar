package com.siscar.siscar_backend.controller;

import com.siscar.siscar_backend.service.ITipoSolicitudService;
import com.siscar.siscar_backend.model.TipoSolicitud;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/tipos-solicitud")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class TipoSolicitudController {
    private final ITipoSolicitudService tipoSolicitudService;

    @GetMapping
    public ResponseEntity<List<TipoSolicitud>>listarTodos(){
        return ResponseEntity.ok(tipoSolicitudService.listarTodos());
    }
}