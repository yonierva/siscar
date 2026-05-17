import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpresaModal } from './empresa-modal';

describe('EmpresaModal', () => {
  let component: EmpresaModal;
  let fixture: ComponentFixture<EmpresaModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpresaModal],
    }).compileComponents();

    fixture = TestBed.createComponent(EmpresaModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
