import { ComponentFixture, TestBed } from '@angular/core/testing';

import { EmpleadoModal } from './empleado-modal';

describe('EmpleadoModal', () => {
  let component: EmpleadoModal;
  let fixture: ComponentFixture<EmpleadoModal>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [EmpleadoModal],
    }).compileComponents();

    fixture = TestBed.createComponent(EmpleadoModal);
    component = fixture.componentInstance;
    await fixture.whenStable();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
