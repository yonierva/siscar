package com.siscar.siscar_backend.service;

import com.siscar.siscar_backend.dto.EmpleadoRequestDTO;
import com.siscar.siscar_backend.dto.EmpleadoResponseDTO;

public interface IEmpleadoService {
    EmpleadoResponseDTO buscarPorCedula(Long cedula);
    EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO dto);
}