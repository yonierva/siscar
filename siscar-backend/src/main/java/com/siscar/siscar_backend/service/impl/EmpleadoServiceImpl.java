
package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.dto.EmpleadoRequestDTO;
import com.siscar.siscar_backend.dto.EmpleadoResponseDTO;
import com.siscar.siscar_backend.model.Empleado;
import com.siscar.siscar_backend.repository.EmpleadoRepository;
import com.siscar.siscar_backend.service.IEmpleadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EmpleadoServiceImpl implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    @Override
    public EmpleadoResponseDTO buscarPorCedula(Long cedula) {
        return empleadoRepository.findByNumeroIdentificacion(cedula)
                .map(this::mapToDTO)
                .orElse(null);
    }

    public List<EmpleadoResponseDTO> listarPorEmpresa(Integer idEmpresa){
        return empleadoRepository.findByIdEmpresa(idEmpresa)
                .stream()
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    @Override
    public EmpleadoResponseDTO crearEmpleado(EmpleadoRequestDTO dto) {
        Empleado empleado = new Empleado();
        empleado.setNombres(dto.getNombres());
        empleado.setApellidos(dto.getApellidos());
        empleado.setNombreCompleto(dto.getNombres() + " " + dto.getApellidos());
        empleado.setTipoIdentificacion(dto.getTipoIdentificacion());
        empleado.setNumeroIdentificacion(dto.getNumeroIdentificacion());
        empleado.setCiudadExpedicion(dto.getCiudadExpedicion());
        empleado.setDireccionResidencia(dto.getDireccionResidencia());
        empleado.setTelefono(dto.getTelefono());
        empleado.setLugarNacimiento(dto.getLugarNacimiento());
        empleado.setCategoria(dto.getCategoria());
        empleado.setTipoSangre(dto.getTipoSangre());
        empleado.setUrlImagen(dto.getUrlImagen());
        empleado.setIdEmpresa(dto.getIdEmpresa());

        Empleado guardado = empleadoRepository.save(empleado);
        return mapToDTO(guardado);
    }

    private EmpleadoResponseDTO mapToDTO(Empleado empleado) {
        EmpleadoResponseDTO dto = new EmpleadoResponseDTO();
        dto.setId(empleado.getId());
        dto.setNombres(empleado.getNombres());
        dto.setApellidos(empleado.getApellidos());
        dto.setNombreCompleto(empleado.getNombreCompleto());
        dto.setTipoIdentificacion(empleado.getTipoIdentificacion());
        dto.setNumeroIdentificacion(empleado.getNumeroIdentificacion());
        dto.setTipoSangre(empleado.getTipoSangre());
        dto.setUrlImagen(empleado.getUrlImagen());
        dto.setCategoria(empleado.getCategoria());
        return dto;
    }
}