import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Empleado } from '../models/empleado.model';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class EmpleadoApi {
  private apiUrl = environment.apiUrl;
  
  constructor(private http: HttpClient) {}

  listarPorEmpresa(idEmpresa: number): Observable<Empleado[]> {
    return this.http.get<Empleado[]>(`${this.apiUrl}/empleados?idEmpresa=${idEmpresa}`);
  }

  buscarPorCedula(cedula: string): Observable<Empleado> {
    return this.http.get<Empleado>(`${this.apiUrl}/empleados/cedula?cedula=${cedula}`);
  }

  crearEmpleado(empleado: Empleado): Observable<Empleado> {
    return this.http.post<Empleado>(`${this.apiUrl}/empleados/crear`, empleado);
  }
}


