import { Component } from '@angular/core';
import { RouterLink, RouterLinkActive, Router } from '@angular/router';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { solicitud } from '../../models/solicitud.model';
import { EmpresaModal } from '../empresa-modal/empresa-modal';

import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material/core';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';

@Component({
  selector: 'app-solicitud',
  imports: [RouterLink, RouterLinkActive, CommonModule, FormsModule, EmpresaModal, MatDatepickerModule, MatNativeDateModule, MatFormFieldModule, MatInputModule],
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


}
