import { TestBed } from '@angular/core/testing';

import { TipoSolicitudApi } from './tipo-solicitud-api';

describe('TipoSolicitudApi', () => {
  let service: TipoSolicitudApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(TipoSolicitudApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
