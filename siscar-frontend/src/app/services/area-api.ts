import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Area } from '../models/area.model';
import { environment } from '../../environments/environments';

@Injectable({
  providedIn: 'root',
})
export class AreaApi {
  private apiUrl = environment.apiUrl;

  constructor(private http: HttpClient) {}

  getAreas(): Observable<Area[]> {
    return this.http.get<Area[]>(`${this.apiUrl}/areas`);
  }
}
