import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PastUserAppointmentsComponent } from './past-user-appointments.component';

describe('PastUserAppointmentsComponent', () => {
  let component: PastUserAppointmentsComponent;
  let fixture: ComponentFixture<PastUserAppointmentsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ PastUserAppointmentsComponent ]
    })
      .compileComponents();

    fixture = TestBed.createComponent(PastUserAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
