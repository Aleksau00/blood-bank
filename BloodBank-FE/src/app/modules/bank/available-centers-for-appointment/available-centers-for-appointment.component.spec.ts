import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AvailableCentersForAppointments } from './available-centers-for-appointment.component';

describe('AvailableCentersForAppointments', () => {
  let component: AvailableCentersForAppointments;
  let fixture: ComponentFixture<AvailableCentersForAppointments>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AvailableCentersForAppointments ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AvailableCentersForAppointments);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
