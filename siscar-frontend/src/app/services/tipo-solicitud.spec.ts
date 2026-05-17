import { TestBed } from '@angular/core/testing';

import { TipoSolicitud } from './tipo-solicitud';

describe('TipoSolicitud', () => {
  let service: TipoSolicitud;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoSolicitud);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
