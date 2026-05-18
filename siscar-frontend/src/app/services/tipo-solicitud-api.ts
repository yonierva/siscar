import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { TipoSolicitud } from '../models/tipo-solicitud.model';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class TipoSolicitudApi {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getTiposSolicitud(): Observable<TipoSolicitud[]> {
    return this.http.get<TipoSolicitud[]>(`${this.apiUrl}/tipos-solicitud`);
  }
}
