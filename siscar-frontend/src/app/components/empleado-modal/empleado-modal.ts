import { Component, Input, Output, EventEmitter, OnChanges, SimpleChanges, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatSelectModule } from '@angular/material/select';
import { EmpleadoApi } from '../../services/empleado-api';
import { Empleado } from '../../models/empleado.model';

import Swal from 'sweetalert2';

@Component({
  selector: 'app-empleado-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
    MatCheckboxModule,
    MatSelectModule
  ],
  templateUrl: './empleado-modal.html',
  styleUrl: './empleado-modal.css'
})
export class EmpleadoModal implements OnChanges {

  @Input() idEmpresa: number = 0;
  @Input() empresaSeleccionada: boolean = false;
  @Output() empleadosActualizados = new EventEmitter<number[]>();

  empleados: Empleado[] = [];
  empleadosSeleccionados: number[] = [];
  cedula: string = '';
  mostrarFormCrear: boolean = false;
  nuevoEmpleado: Empleado = {
    id: 0,
    idEmpresa: 0,
    nombres: '',
    apellidos: '',
    nombreCompleto: '',
    tipoIdentificacion: '',
    numeroIdentificacion: 0,
    tipoSangre: '',
    urlImagen: '',
  };

  tiposIdentificacion = [
    { value: 'C.C.', label: 'Cédula de Ciudadanía' },
    { value: 'T.I.', label: 'Tarjeta de Identidad' },
    { value: 'P.S.', label: 'Pasaporte' }
  ];

  tiposSangre = [
    { value: 'A+', label: 'A+' },
    { value: 'A-', label: 'A-' },
    { value: 'B+', label: 'B+' },
    { value: 'B-', label: 'B-' },
    { value: 'AB+', label: 'AB+' },
    { value: 'AB-', label: 'AB-' },
    { value: 'O+', label: 'O+' },
    { value: 'O-', label: 'O-' }
  ];

  constructor(
    private empleadoApi: EmpleadoApi,
    private cdr: ChangeDetectorRef
  ) { }

  ngOnChanges(changes: SimpleChanges) {
    if (changes['idEmpresa'] && this.idEmpresa > 0) {
      this.cargarEmpleados();
    }
  }

  cargarEmpleados() {
    this.empleadoApi.listarPorEmpresa(this.idEmpresa).subscribe({
      next: (response) => {
        this.empleados = response;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error al cargar empleados', error);
      }
    });
  }

  buscarPorCedula() {
    if (!this.cedula) return;
    this.empleadoApi.buscarPorCedula(this.cedula).subscribe({
      next: (response) => {
        this.empleados = [response];
        this.mostrarFormCrear = false;
        this.cdr.detectChanges();
        console.log(this.empleados);
      },
      error: (error) => {
        if (error.status === 404) {
          this.empleados = [];
          this.mostrarFormCrear = true;
          this.nuevoEmpleado.numeroIdentificacion = Number(this.cedula);
          this.cdr.detectChanges();
        }
      }
    });
  }

  crearEmpleado() {
    this.nuevoEmpleado.idEmpresa = this.idEmpresa;
    this.nuevoEmpleado.nombreCompleto = `${this.nuevoEmpleado.nombres} ${this.nuevoEmpleado.apellidos}`;
    this.empleadoApi.crearEmpleado(this.nuevoEmpleado).subscribe({
      next: (response) => {
        this.empleados.push(response);
        this.mostrarFormCrear = false;
        this.cedula = '';
        this.cdr.detectChanges();
        Swal.fire({
          icon: 'success',
          title: 'Empleado creado',
          text: 'El empleado ha sido creado exitosamente.',
          timer: 2000,
          showConfirmButton: false
        });
      },
      error: (error) => {
        console.error('Error al crear empleado', error);
        Swal.fire({
          icon: 'error',
          title: 'Error',
          text: 'Hubo un error al crear el empleado. Por favor, intenta nuevamente.',
          timer: 2000,
          showConfirmButton: false
        });
      }
    });
  }

  selectEmpleado(id: number, checked: boolean) {
    if (checked) {
      this.empleadosSeleccionados.push(id);
    } else {
      this.empleadosSeleccionados = this.empleadosSeleccionados.filter(e => e !== id);
    }
    this.empleadosActualizados.emit(this.empleadosSeleccionados);
  }

  limpiarSeleccion() {
    this.cargarEmpleados();
  }
}