import { Routes } from '@angular/router';
import { Solicitud } from './components/solicitud/solicitud';
import { StickerPreview } from './components/sticker-preview/sticker-preview';

export const routes: Routes = [
    { 
        path: 'solicitud', 
        component: Solicitud
    },
    { 
        path: 'sticker-preview', 
        component: StickerPreview
    }
];
