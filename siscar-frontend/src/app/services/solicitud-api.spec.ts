import { TestBed } from '@angular/core/testing';

import { SolicitudApi } from './solicitud-api';

describe('SolicitudApi', () => {
  let service: SolicitudApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(SolicitudApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
