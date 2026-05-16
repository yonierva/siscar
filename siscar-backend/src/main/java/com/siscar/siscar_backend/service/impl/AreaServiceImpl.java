package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.model.Area;
import com.siscar.siscar_backend.repository.AreaRepository;
import com.siscar.siscar_backend.service.IAreaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements IAreaService {

    private final AreaRepository areaRepository;

    @Override
    public List<Area> listarTodas() {
        return areaRepository.findAll();
    }
}