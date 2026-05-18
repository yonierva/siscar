import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from "../../environments/environments";


@Injectable({
  providedIn: 'root',
})
export class EmpresaApi {
  private apiUrl = `${environment.apiUrl}/empresas`;

  constructor(private http: HttpClient) {}

  getEmpresas(nombre: string): Observable<any> {
    return this.http.get(`${this.apiUrl}?nombre=${nombre}`);
  }

}
