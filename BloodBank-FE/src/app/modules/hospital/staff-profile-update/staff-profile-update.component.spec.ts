import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StaffProfileUpdateComponent } from './staff-profile-update.component';

describe('StaffProfileUpdateComponent', () => {
  let component: StaffProfileUpdateComponent;
  let fixture: ComponentFixture<StaffProfileUpdateComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StaffProfileUpdateComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(StaffProfileUpdateComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
