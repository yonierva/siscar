import { Component } from '@angular/core';
import { Empleado } from "../../models/empleado.model"
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-empleado-modal',
  imports: [CommonModule, FormsModule],
  templateUrl: './empleado-modal.html',
  styleUrl: './empleado-modal.css',
})
export class EmpleadoModal {

  empleado: Empleado = {
    id: 0,
    nombres: '',
    apellidos: '',
    nombreCompleto: '',
    tipoIdentificacion: '',
    numeroIdentificacion: 0,
    tipoSangre: '',
    urlImagen: '',
    categoria: ''
  }
}
