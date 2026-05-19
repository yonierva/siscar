import { TestBed } from '@angular/core/testing';

import { SolicitudEstadoApi } from './solicitud-estado-api';

describe('SolicitudEstadoApi', () => {
  let service: SolicitudEstadoApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudEstadoApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
