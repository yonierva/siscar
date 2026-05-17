export interface solicitud{
    idEmpresa: number;
    fechaEntrada: string;
    fechaSalida: string;
    horaEntrada: string;
    horaSalida: string;
    esTemporal: boolean;
    idTipoSolicitud: number;
    areasSeleccionadas: number[];
    empleadosSeleccionados: number[];
    nombreJefeInmediato: string;
}