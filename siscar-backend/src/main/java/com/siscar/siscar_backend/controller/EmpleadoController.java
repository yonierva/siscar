package com.siscar.siscar_backend.controller;

import com.siscar.siscar_backend.service.IEmpleadoService;
import com.siscar.siscar_backend.dto.EmpleadoResponseDTO;
import com.siscar.siscar_backend.dto.EmpleadoRequestDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/empleados")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")

public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    @GetMapping
    public ResponseEntity<List<EmpleadoResponseDTO>>listarPorEmpresa(@RequestParam Integer idEmpresa){
        return ResponseEntity.ok(empleadoService.listarPorEmpresa(idEmpresa));
    }

    @GetMapping("/cedula")
    public ResponseEntity<EmpleadoResponseDTO>buscarPorCedula(@RequestParam Long cedula){
        EmpleadoResponseDTO empleado = empleadoService.buscarPorCedula(cedula);
        if (empleado == null){
           return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(empleado);
    }

    @PostMapping("/crear")
    public ResponseEntity<EmpleadoResponseDTO>crearEmpleado(@RequestBody EmpleadoRequestDTO dto){
        return ResponseEntity.ok(empleadoService.crearEmpleado(dto));
    }
}