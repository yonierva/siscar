package com.siscar.siscar_backend.service;

import com.siscar.siscar_backend.dto.SolicitudRequestDTO;
import com.siscar.siscar_backend.dto.StickerResponseDTO;
import java.util.List;

public interface ISolicitudService {
    Integer crearDraft(SolicitudRequestDTO dto);
    List<StickerResponseDTO> guardarSolicitud(Integer idSolicitud);
    void actualizarDraft(Integer idSolicitud, SolicitudRequestDTO dto);
}