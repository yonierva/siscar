package com.siscar.siscar_backend.controller;

import com.siscar.siscar_backend.model.Empresa;
import com.siscar.siscar_backend.service.IEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empresas")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class EmpresaController {

    private final IEmpresaService empresaService;

    @GetMapping
    public ResponseEntity<List<Empresa>> buscar(@RequestParam String nombre) {
        return ResponseEntity.ok(empresaService.buscarPorNombre(nombre));
    }
}