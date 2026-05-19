import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { solicitud } from '../models/solicitud.model';
import { Sticker } from '../models/sticker.model';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class SolicitudApi {
  private apiUrl =`${environment.apiUrl}/solicitud`;

  constructor(private http: HttpClient) {}

  guardarSolicitud(solicitud: solicitud): Observable<Sticker[]> {
    return this.http.post<Sticker[]>(`${this.apiUrl}/guardar`, solicitud);
  }
}
