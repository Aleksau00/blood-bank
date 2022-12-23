import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppointmentStaffComponent } from './appointment-staff.component';

describe('AppointmentStaffComponent', () => {
  let component: AppointmentStaffComponent;
  let fixture: ComponentFixture<AppointmentStaffComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AppointmentStaffComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AppointmentStaffComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
