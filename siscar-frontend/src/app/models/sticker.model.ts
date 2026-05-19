export interface Sticker {
    id: number;
    idSolicitud: number;
    nombreCompleto: string;
    numeroIdentificacion: number;
    tipoIdentificacion: string;
    urlImagen: string;
    fechaEntrada: string;
    fechaSalida: string;
    horaEntrada: string;
    horaSalida: string;
    esTemporal: boolean;
    areas: string[];
    razonSocialEmpresa: string;
}