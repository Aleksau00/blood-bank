import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCenterAppointmentComponent } from './add-center-appointment.component';

describe('AddCenterAppointmentComponent', () => {
  let component: AddCenterAppointmentComponent;
  let fixture: ComponentFixture<AddCenterAppointmentComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCenterAppointmentComponent ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(AddCenterAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
