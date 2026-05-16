package com.siscar.siscar_backend.service;

import com.siscar.siscar_backend.model.Empresa;
import java.util.List;

public interface IEmpresaService {
    List<Empresa> buscarPorNombre(String nombre);
}