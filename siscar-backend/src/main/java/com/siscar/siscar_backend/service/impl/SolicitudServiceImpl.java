package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.dto.SolicitudRequestDTO;
import com.siscar.siscar_backend.dto.StickerResponseDTO;
import com.siscar.siscar_backend.model.*;
import com.siscar.siscar_backend.repository.*;
import com.siscar.siscar_backend.service.ISolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SolicitudServiceImpl implements ISolicitudService {

    private final SolicitudRepository solicitudRepository;
    private final SolicitudAreaRepository solicitudAreaRepository;
    private final SolicitudEmpleadoRepository solicitudEmpleadoRepository;
    private final StickerRepository stickerRepository;
    private final EmpleadoRepository empleadoRepository;
    private final EmpresaRepository empresaRepository;
    private final AreaRepository areaRepository;

    @Override
    @Transactional
    public List<StickerResponseDTO> guardarSolicitud(SolicitudRequestDTO dto) {

        // 1. Guardar Solicitud
        Solicitud solicitud = new Solicitud();
        solicitud.setTiempoSolicitado(LocalDateTime.now().toString());
        solicitud.setFechaSolicitud(LocalDate.now());
        solicitud.setIdEmpresa(dto.getIdEmpresa());
        solicitud.setIdTipoSolicitud(dto.getIdTipoSolicitud());
        solicitud.setHoraEntrada(dto.getHoraEntrada());
        solicitud.setHoraSalida(dto.getHoraSalida());
        solicitud.setNombreJefeInmediato(dto.getNombreJefeInmediato());
        solicitud.setActividadRealizar(dto.getActividadRealizar());
        solicitud.setCorreoFacturacion(dto.getCorreoFacturacion());
        solicitud.setSolicitadoPor(dto.getSolicitadoPor());
        solicitud.setImprimioSolicitud(false);
        Solicitud solicitudGuardada = solicitudRepository.save(solicitud);

        // 2. Guardar Solicitudes_Areas
        for (Integer idArea : dto.getAreasSeleccionadas()) {
            SolicitudArea sa = new SolicitudArea();
            sa.setIdSolicitud(solicitudGuardada.getId());
            sa.setIdArea(idArea);
            solicitudAreaRepository.save(sa);
        }

        // 3. Guardar SolicitudEmpleado y Stickers
        List<StickerResponseDTO> stickers = new ArrayList<>();
        for (Integer idEmpleado : dto.getEmpleadosSeleccionados()) {

            // SolicitudEmpleado
            SolicitudEmpleado se = new SolicitudEmpleado();
            se.setIdEmpleado(idEmpleado);
            se.setIdSolicitud(solicitudGuardada.getId());
            solicitudEmpleadoRepository.save(se);

            // Sticker
            Sticker sticker = new Sticker();
            sticker.setIdEmpleado(idEmpleado);
            sticker.setIdSolicitud(solicitudGuardada.getId());
            sticker.setFechaEntrada(dto.getFechaEntrada());
            sticker.setFechaSalida(dto.getFechaSalida());
            sticker.setHoraEntrada(dto.getHoraEntrada());
            sticker.setHoraSalida(dto.getHoraSalida());
            sticker.setEsTemporal(dto.getEsTemporal());
            sticker.setDevolvioStickers(false);
            stickerRepository.save(sticker);

            stickers.add(buildStickerResponse(sticker, idEmpleado, dto.getAreasSeleccionadas(), dto.getIdEmpresa()));
        }

        return stickers;
    }

    private StickerResponseDTO buildStickerResponse(Sticker sticker,
                                                    Integer idEmpleado, List<Integer> idAreas, Integer idEmpresa) {

        StickerResponseDTO dto = new StickerResponseDTO();
        dto.setId(sticker.getId());
        dto.setFechaEntrada(sticker.getFechaEntrada());
        dto.setFechaSalida(sticker.getFechaSalida());
        dto.setHoraEntrada(sticker.getHoraEntrada());
        dto.setHoraSalida(sticker.getHoraSalida());
        dto.setEsTemporal(sticker.getEsTemporal());

        empleadoRepository.findById(idEmpleado).ifPresent(emp -> {
            dto.setIdEmpleado(emp.getId());
            dto.setNombreCompleto(emp.getNombreCompleto());
            dto.setNumeroIdentificacion(emp.getNumeroIdentificacion());
            dto.setTipoIdentificacion(emp.getTipoIdentificacion());
            dto.setUrlImagen(emp.getUrlImagen());
        });

        empresaRepository.findById(idEmpresa).ifPresent(emp ->
                dto.setRazonSocialEmpresa(emp.getRazonSocial())
        );

        List<String> nombresAreas = new ArrayList<>();
        for (Integer idArea : idAreas) {
            areaRepository.findById(idArea).ifPresent(area ->
                    nombresAreas.add(area.getCarnet())
            );
        }
        dto.setAreas(nombresAreas);

        return dto;
    }

}