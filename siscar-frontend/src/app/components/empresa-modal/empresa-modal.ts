import { Component, Output, EventEmitter,ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MatDialogModule, MatDialog } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { EmpresaApi } from '../../services/empresa-api';
import { Empresa } from '../../models/empresa.model';
import {
  debounceTime,
  distinctUntilChanged,
  pipe,
  Subject,
  Subscription,
} from 'rxjs';

@Component({
  selector: 'app-empresa-modal',
  standalone: true,
  imports: [
    CommonModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule,
  ],
  templateUrl: './empresa-modal.html',
  styleUrl: './empresa-modal.css',
})
export class EmpresaModal {

  @Output() empresaSeleccionada = new EventEmitter<Empresa>();
  @Output() busquedaEmpresaRealizada = new EventEmitter<boolean>();

  busqueda: string = '';
  empresas: Empresa[] = [];
  modalAbierto: boolean = false;
  empresaElegida: Empresa | null = null;

  empresaSubject: Subject<string> = new Subject<string>();

  constructor(private empresaApi: EmpresaApi, private cdr: ChangeDetectorRef) { }

  ngOnInit() {
    this.empresaSubject.pipe(debounceTime(700),distinctUntilChanged())
      .subscribe((busqueda) => {
        this.buscarEmpresas(busqueda);
      });
  }

  onBusquedaChange() {
    this.empresaSubject.next(this.busqueda);
  }

  abrirModal() {
    this.modalAbierto = true;
  }

  cerrarModal() {
    this.modalAbierto = false;
    this.busqueda = '';
    this.empresas = [];
  }

  buscarEmpresas(busqueda: string) {
    if (busqueda.trim().length === 0) return;
    this.empresaApi.getEmpresas(busqueda).subscribe({
      next: (response) => {
        this.empresas = response;
        this.cdr.detectChanges();
      },
      error: (error) => {
        console.error('Error al buscar empresas', error);
      }
    });
  }

  seleccionar(empresa: Empresa) {
    this.empresaElegida = empresa;
    this.empresaSeleccionada.emit(empresa);
    this.busquedaEmpresaRealizada.emit(true);
    this.cerrarModal();
    console.log('Empresa seleccionada:', this.empresaElegida.razonSocial);
    this.cdr.markForCheck();
  }
}