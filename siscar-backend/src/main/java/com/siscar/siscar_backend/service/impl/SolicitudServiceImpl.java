package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.dto.SolicitudRequestDTO;
import com.siscar.siscar_backend.dto.StickerResponseDTO;
import com.siscar.siscar_backend.model.*;
import com.siscar.siscar_backend.repository.*;
import com.siscar.siscar_backend.service.ISolicitudService;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
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

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public Integer crearDraft(SolicitudRequestDTO dto) {

        if (dto.getIdEmpresa() == null)
            throw new IllegalArgumentException("Debe seleccionar una empresa");
        if (dto.getIdTipoSolicitud() == null)
            throw new IllegalArgumentException("Debe seleccionar un tipo de solicitud");
        if (dto.getAreasSeleccionadas() == null || dto.getAreasSeleccionadas().isEmpty())
            throw new IllegalArgumentException("Debe seleccionar al menos un área");
        if (dto.getEmpleadosSeleccionados() == null || dto.getEmpleadosSeleccionados().isEmpty())
            throw new IllegalArgumentException("Debe seleccionar al menos un empleado");
        if (dto.getFechaEntrada() == null)
            throw new IllegalArgumentException("La fecha de entrada es obligatoria");
        if (dto.getFechaSalida() == null)
            throw new IllegalArgumentException("La fecha de salida es obligatoria");
        if (dto.getHoraEntrada() == null || dto.getHoraEntrada().isEmpty())
            throw new IllegalArgumentException("La hora de entrada es obligatoria");
        if (dto.getHoraSalida() == null || dto.getHoraSalida().isEmpty())
            throw new IllegalArgumentException("La hora de salida es obligatoria");

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
        solicitud.setEstado("draft");
        Solicitud solicitudGuardada = solicitudRepository.save(solicitud);

        // 2. Guardar Solicitudes_Areas
        for (Integer idArea : dto.getAreasSeleccionadas()) {
            SolicitudArea sa = new SolicitudArea();
            sa.setIdSolicitud(solicitudGuardada.getId());
            sa.setIdArea(idArea);
            solicitudAreaRepository.save(sa);
        }

        // 3. Guardar SolicitudEmpleado
        for (Integer idEmpleado : dto.getEmpleadosSeleccionados()) {

            // SolicitudEmpleado
            SolicitudEmpleado se = new SolicitudEmpleado();
            se.setIdEmpleado(idEmpleado);
            se.setIdSolicitud(solicitudGuardada.getId());
            solicitudEmpleadoRepository.save(se);
        }

        return solicitudGuardada.getId();
    }

    @Override
    @Transactional
    public List<StickerResponseDTO> guardarSolicitud(Integer idSolicitud) {

        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        List<SolicitudEmpleado> empleados =
                solicitudEmpleadoRepository.findByIdSolicitud(idSolicitud);

        List<SolicitudArea> areas =
                solicitudAreaRepository.findByIdSolicitud(idSolicitud);

        List<Integer> idsAreas = areas.stream()
                .map(SolicitudArea::getIdArea)
                .toList();

        List<StickerResponseDTO> stickers = new ArrayList<>();

        for (SolicitudEmpleado se : empleados) {

            Sticker sticker = new Sticker();

            sticker.setIdEmpleado(se.getIdEmpleado());
            sticker.setIdSolicitud(idSolicitud);

            sticker.setFechaEntrada(solicitud.getFechaSolicitud());
            sticker.setFechaSalida(solicitud.getFechaSolicitud());

            sticker.setHoraEntrada(solicitud.getHoraEntrada());
            sticker.setHoraSalida(solicitud.getHoraSalida());

            sticker.setEsTemporal(true);

            sticker.setDevolvioStickers(false);

            stickerRepository.save(sticker);

            stickers.add(
                    buildStickerResponse(
                            sticker,
                            se.getIdEmpleado(),
                            idsAreas,
                            solicitud.getIdEmpresa()
                    )
            );
        }

        // COMPLETAR
        solicitud.setEstado("completed");

        solicitudRepository.save(solicitud);

        return stickers;
    }

    private StickerResponseDTO buildStickerResponse(Sticker sticker,
                                                    Integer idEmpleado, List<Integer> idAreas, Integer idEmpresa) {

        StickerResponseDTO dto = new StickerResponseDTO();
        dto.setId(sticker.getId());
        dto.setIdSolicitud(sticker.getIdSolicitud());
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

    @Override
    @Transactional
    public void actualizarDraft(Integer idSolicitud, SolicitudRequestDTO dto) {

        Solicitud solicitud = solicitudRepository.findById(idSolicitud)
                .orElseThrow(() -> new RuntimeException("Solicitud no encontrada"));

        // ACTUALIZAR SOLICITUD
        solicitud.setIdEmpresa(dto.getIdEmpresa());

        solicitud.setIdTipoSolicitud(dto.getIdTipoSolicitud());

        solicitud.setHoraEntrada(dto.getHoraEntrada());
        solicitud.setHoraSalida(dto.getHoraSalida());

        solicitud.setNombreJefeInmediato(dto.getNombreJefeInmediato());

        solicitud.setActividadRealizar(dto.getActividadRealizar());

        solicitud.setCorreoFacturacion(dto.getCorreoFacturacion());

        solicitud.setSolicitadoPor(dto.getSolicitadoPor());

        solicitudRepository.save(solicitud);

        // ELIMINAR ÁREAS ANTERIORES
        solicitudAreaRepository.deleteByIdSolicitud(idSolicitud);
        entityManager.flush();

        // CREAR NUEVAS ÁREAS
        for (Integer idArea : dto.getAreasSeleccionadas()) {

            SolicitudArea sa = new SolicitudArea();

            sa.setIdSolicitud(idSolicitud);
            sa.setIdArea(idArea);

            solicitudAreaRepository.save(sa);
        }

        // ELIMINAR EMPLEADOS ANTERIORES
        solicitudEmpleadoRepository.deleteByIdSolicitud(idSolicitud);
        entityManager.flush();

        // CREAR NUEVOS EMPLEADOS
        for (Integer idEmpleado : dto.getEmpleadosSeleccionados()) {

            SolicitudEmpleado se = new SolicitudEmpleado();

            se.setIdSolicitud(idSolicitud);
            se.setIdEmpleado(idEmpleado);

            solicitudEmpleadoRepository.save(se);
        }
    }

}