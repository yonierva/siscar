package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.model.TipoSolicitud;
import com.siscar.siscar_backend.repository.TipoSolicitudRepository;
import com.siscar.siscar_backend.service.ITipoSolicitudService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoSolicitudServiceImpl implements ITipoSolicitudService {

    private final TipoSolicitudRepository tipoSolicitudRepository;

    @Override
    public List<TipoSolicitud> listarTodos() {
        return tipoSolicitudRepository.findAll();
    }
}