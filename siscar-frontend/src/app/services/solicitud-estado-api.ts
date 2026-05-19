import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class SolicitudEstadoApi {

    private idSolicitudSubject = new BehaviorSubject<number | null>(null);
    idSolicitud$ = this.idSolicitudSubject.asObservable();

    setIdSolicitud(id: number) {
      this.idSolicitudSubject.next(id);
    }

    getIdSolicitud(): number | null {
      if (this.idSolicitudSubject.getValue() === null) {
        console.warn('No se ha establecido un ID de solicitud.');
      }
      return this.idSolicitudSubject.getValue();
    }
}
