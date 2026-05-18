import { TestBed } from '@angular/core/testing';

import { EmpleadoApi } from './empleado-api';

describe('EmpleadoApi', () => {
  let service: EmpleadoApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(EmpleadoApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
