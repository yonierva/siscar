import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

import { solicitud } from '../../models/solicitud.model';
import { EmpresaModal } from '../empresa-modal/empresa-modal';
import { TipoSolicitud } from '../../models/tipo-solicitud.model';
import { Area } from '../../models/area.model';

import { TipoSolicitudApi } from '../../services/tipo-solicitud-api';
import { AreaApi } from '../../services/area-api';
import { SolicitudApi } from '../../services/solicitud-api';

import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-solicitud',
  standalone: true,
  imports: [CommonModule, FormsModule, 
            EmpresaModal, MatDatepickerModule, 
            MatNativeDateModule, MatFormFieldModule, 
            MatInputModule, MatCheckboxModule, 
            MatSelectModule],
  templateUrl: './solicitud.html',
  styleUrl: './solicitud.css',
})
export class Solicitud {

  solicitud: solicitud = {
    idEmpresa: 0,
    fechaEntrada: '',
    fechaSalida: '',
    horaEntrada: '',
    horaSalida: '',
    esTemporal: false,
    idTipoSolicitud: 0,
    areasSeleccionadas: [],
    empleadosSeleccionados: [],
    nombreJefeInmediato: ''
  }

  horas: string[] = [
  '06:00 AM',
  '07:00 AM',
  '08:00 AM',
  '09:00 AM',
  '10:00 AM',
  '11:00 AM',
  '12:00 PM',
  '01:00 PM',
  '02:00 PM',
  '03:00 PM',
  '04:00 PM',
  '05:00 PM'
];

  tiposSolicitud: TipoSolicitud[] = [];
  areas: Area[] = [];

  constructor(private tipoSolicitudApi: TipoSolicitudApi, private areaApi: AreaApi, private solicitudApi: SolicitudApi, private chr: ChangeDetectorRef) {}

  ngOnInit() {
    this.obtenerTiposSolicitud();
    this.obtenerAreas();
  }

  guardarSolicitud() {
      this.solicitudApi.guardarSolicitud(this.solicitud).subscribe({
        next: (response) => {
          console.log('Solicitud enviada con éxito', response);
        },
        error: (error) => {
          console.error('Error al enviar la solicitud', error);
        }
      });
  }

  obtenerTiposSolicitud() {
    this.tipoSolicitudApi.getTiposSolicitud().subscribe({
      next: (Response) =>{
        this.tiposSolicitud = Response;
        this.chr.detectChanges();
      },error: (error) => {
        console.error('Error al obtener los tipos de solicitud', error);
      }
    });
  }

  obtenerAreas() {
    this.areaApi.getAreas().subscribe({
      next: (Response) =>{
        this.areas = Response;
        this.chr.detectChanges();
      },error: (error) => {
        console.error('Error al obtener las áreas', error);
      }
    });
  }

  selectArea(areaId: number, checked: boolean) {
    if(checked){
      this.solicitud.areasSeleccionadas.push(areaId);
    }else{
      this.solicitud.areasSeleccionadas = this.solicitud.areasSeleccionadas.filter(id => id !== areaId);
    }
  }
}
