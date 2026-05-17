package com.siscar.siscar_backend.service;

import com.siscar.siscar_backend.dto.StickerResponseDTO;
import java.util.List;

public interface IStickerService {
    List<StickerResponseDTO> listarPorSolicitud(Integer idSolicitud);
}