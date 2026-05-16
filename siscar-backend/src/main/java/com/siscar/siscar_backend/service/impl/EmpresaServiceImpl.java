package com.siscar.siscar_backend.service.impl;

import com.siscar.siscar_backend.model.Empresa;
import com.siscar.siscar_backend.repository.EmpresaRepository;
import com.siscar.siscar_backend.service.IEmpresaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EmpresaServiceImpl implements IEmpresaService {

    private final EmpresaRepository empresaRepository;

    @Override
    public List<Empresa> buscarPorNombre(String nombre) {
        return empresaRepository.findByRazonSocialContainingIgnoreCase(nombre);
    }
}