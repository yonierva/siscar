package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.dto.StickerResponseDTO;
import com.siscar.siscar_backend.model.Solicitud;
import com.siscar.siscar_backend.model.SolicitudArea;
import com.siscar.siscar_backend.model.Sticker;
import com.siscar.siscar_backend.repository.*;
import com.siscar.siscar_backend.service.IStickerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class StickerServiceImpl implements IStickerService {

    private final StickerRepository stickerRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;
    private final AreaRepository areaRepository;
    private final SolicitudRepository solicitudRepository;
    private final SolicitudAreaRepository solicitudAreaRepository;

    @Override
    public List<StickerResponseDTO> listarPorSolicitud(Integer idSolicitud) {
        List<Sticker> stickers = stickerRepository.findByIdSolicitud(idSolicitud);
        List<StickerResponseDTO> response = new ArrayList<>();

        Integer idEmpresa = solicitudRepository.findById(idSolicitud)
                .map(Solicitud::getIdEmpresa)
                .orElse(null);

        List<Integer> idAreas = solicitudAreaRepository
                .findByIdSolicitud(idSolicitud).stream()
                .map(SolicitudArea::getIdArea)
                .toList();

        for (Sticker sticker : stickers) {
            StickerResponseDTO dto = new StickerResponseDTO();
            dto.setId(sticker.getId());
            dto.setFechaEntrada(sticker.getFechaEntrada());
            dto.setFechaSalida(sticker.getFechaSalida());
            dto.setHoraEntrada(sticker.getHoraEntrada());
            dto.setHoraSalida(sticker.getHoraSalida());
            dto.setEsTemporal(sticker.getEsTemporal());

            empleadoRepository.findById(sticker.getIdEmpleado()).ifPresent(emp -> {
                dto.setIdEmpleado(emp.getId());
                dto.setNombreCompleto(emp.getNombreCompleto());
                dto.setNumeroIdentificacion(emp.getNumeroIdentificacion());
                dto.setTipoIdentificacion(emp.getTipoIdentificacion());
                dto.setUrlImagen(emp.getUrlImagen());
            });

            if (idEmpresa != null) {
                empresaRepository.findById(idEmpresa).ifPresent(emp ->
                        dto.setRazonSocialEmpresa(emp.getRazonSocial())
                );
            }

            List<String> nombresAreas = new ArrayList<>();
            for (Integer idArea : idAreas) {
                areaRepository.findById(idArea).ifPresent(area ->
                        nombresAreas.add(area.getCarnet())
                );
            }
            dto.setAreas(nombresAreas);
            response.add(dto);
        }
        return response;
    }
}