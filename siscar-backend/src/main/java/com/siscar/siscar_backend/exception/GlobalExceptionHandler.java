package com.siscar.siscar_backend.exception;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(
            DataIntegrityViolationException ex) {
        Map<String, Object> error = new HashMap<>();
        error.put("timestamp", LocalDateTime.now().toString());
        error.put("status", 400);

        String mensaje = ex.getMostSpecificCause().getMessage();

        if (mensaje.contains("FK_Solicitudes_Empresas")) {
            error.put("mensaje", "La empresa seleccionada no existe");
        } else if (mensaje.contains("FK_Solicitudes_Tipos_Solicitud")) {
            error.put("mensaje", "El tipo de solicitud seleccionado no existe");
        } else if (mensaje.contains("FK_Solicitudes_Areas")) {
            error.put("mensaje", "Una de las áreas seleccionadas no existe");
        } else if (mensaje.contains("FK_Stickers")) {
            error.put("mensaje", "Error al crear el sticker — empleado o solicitud no existe");
        } else {
            error.put("mensaje", "Error de integridad en la base de datos");
            error.put("detalle", mensaje);
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}