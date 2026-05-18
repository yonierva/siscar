import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { ChangeDetectorRef } from '@angular/core';

import { EmpresaModal } from '../empresa-modal/empresa-modal';
import { EmpleadoModal } from '../empleado-modal/empleado-modal';

import { solicitud } from '../../models/solicitud.model';
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
import { Empresa } from '../../models/empresa.model';

@Component({
  selector: 'app-solicitud',
  standalone: true,
  imports: [CommonModule, FormsModule,
    EmpresaModal,
    MatDatepickerModule,
    MatNativeDateModule, MatFormFieldModule,
    MatInputModule, MatCheckboxModule,
    MatSelectModule, EmpleadoModal],
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
    '06:00',
    '07:00',
    '08:00',
    '09:00',
    '10:00',
    '11:00',
    '12:00',
    '13:00',
    '14:00',
    '15:00',
    '16:00',
    '17:00',
    '18:00',
    '19:00',
    '20:00',
    '21:00',
    '22:00',
    '23:00',
  ];

  tiposSolicitud: TipoSolicitud[] = [];
  areas: Area[] = [];

  empresaNombre: string = '';

  constructor(private tipoSolicitudApi: TipoSolicitudApi, private areaApi: AreaApi, private solicitudApi: SolicitudApi, private chr: ChangeDetectorRef) { }

  ngOnInit() {
    this.obtenerTiposSolicitud();
    this.obtenerAreas();
  }

  onEmpresaSeleccionada(empresa: Empresa) {
    this.solicitud.idEmpresa = empresa.id;
    this.empresaNombre = empresa.razonSocial;
  }

  onEmpleadosActualizados(ids: number[]) {
    this.solicitud.empleadosSeleccionados = ids;
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
      next: (Response) => {
        this.tiposSolicitud = Response;
        this.chr.detectChanges();
      }, error: (error) => {
        console.error('Error al obtener los tipos de solicitud', error);
      }
    });
  }

  obtenerAreas() {
    this.areaApi.getAreas().subscribe({
      next: (Response) => {
        this.areas = Response;
        this.chr.detectChanges();
      }, error: (error) => {
        console.error('Error al obtener las áreas', error);
      }
    });
  }

  selectArea(areaId: number, checked: boolean) {
    if (checked) {
      this.solicitud.areasSeleccionadas.push(areaId);
    } else {
      this.solicitud.areasSeleccionadas = this.solicitud.areasSeleccionadas.filter(id => id !== areaId);
    }
  }
}
