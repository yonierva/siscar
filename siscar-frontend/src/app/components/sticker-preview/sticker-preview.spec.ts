import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StickerPreview } from './sticker-preview';

describe('StickerPreview', () => {
  let component: StickerPreview;
  let fixture: ComponentFixture<StickerPreview>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [StickerPreview],
    }).compileComponents();

    fixture = TestBed.createComponent(StickerPreview);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
