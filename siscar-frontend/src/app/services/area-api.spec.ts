import { TestBed } from '@angular/core/testing';

import { AreaApi } from './area-api';

describe('AreaApi', () => {
  let service: AreaApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(AreaApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
