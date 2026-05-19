import { Component, ChangeDetectorRef } from '@angular/core';
import { CommonModule } from '@angular/common';

import { Sticker } from '../../models/sticker.model';

import { SolicitudEstadoApi } from '../../services/solicitud-estado-api';
import { StickerApi } from '../../services/sticker-api';

@Component({
  selector: 'app-sticker-preview',
  imports: [CommonModule],
  templateUrl: './sticker-preview.html',
  styleUrl: './sticker-preview.css',
})
export class StickerPreview {
  stickers: Sticker[] = [];
  stickerSeleccionado: Sticker | null = null;

  constructor( private stickerApi: StickerApi,private solicitudEstadoApi: SolicitudEstadoApi, private cdr: ChangeDetectorRef) {}

  ngOnInit() {
    const idSolicitud = this.solicitudEstadoApi.getIdSolicitud();
    if (idSolicitud !== null) {
      this.stickerApi.getStickers(idSolicitud).subscribe({
        next: (response) => {
          this.stickers = response;
          console.log('Stickers obtenidos con éxito', this.stickers);
          this.cdr.detectChanges();
        },
        error: (error) => {
          console.error('Error al obtener los stickers', error);
        }
      });
    } else {
      console.warn('No se ha establecido un ID de solicitud para obtener los stickers.');
    }
  }

  verSticker(sticker: Sticker) {
    this.stickerSeleccionado = sticker;
  }
}
