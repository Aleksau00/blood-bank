import { ComponentFixture, TestBed } from '@angular/core/testing';

import { HistoryExaminationsComponent } from './history-examinations.component';

describe('HistoryExaminationsComponent', () => {
  let component: HistoryExaminationsComponent;
  let fixture: ComponentFixture<HistoryExaminationsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ HistoryExaminationsComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(HistoryExaminationsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
