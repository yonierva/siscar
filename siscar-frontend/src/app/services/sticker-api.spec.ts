import { TestBed } from '@angular/core/testing';

import { StickerApi } from './sticker-api';

describe('StickerApi', () => {
  let service: StickerApi;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(StickerApi);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
