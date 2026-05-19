import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { solicitud } from '../models/solicitud.model';
import { Sticker } from '../models/sticker.model';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class StickerApi {
  private apiUrl =`${environment.apiUrl}/stickers`;

  constructor(private http: HttpClient) {}

  getStickers(idSolicitud: number): Observable<Sticker[]> {
    return this.http.get<Sticker[]>(`${this.apiUrl}?idSolicitud=${idSolicitud}`);
  }
}
