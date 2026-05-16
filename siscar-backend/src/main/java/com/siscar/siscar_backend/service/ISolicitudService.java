package com.siscar.siscar_backend.service;

import com.siscar.siscar_backend.dto.SolicitudRequestDTO;
import com.siscar.siscar_backend.dto.StickerResponseDTO;
import java.util.List;

public interface ISolicitudService {
    List<StickerResponseDTO> guardarSolicitud(SolicitudRequestDTO dto);
}