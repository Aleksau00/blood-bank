import { Component, OnInit } from '@angular/core';
import { AppointmentUpdate } from '../../bank/model/appointmentUpdate.model';


@Component({
  selector: 'app-history-examinations',
  templateUrl: './history-examinations.component.html',
  styleUrls: ['./history-examinations.component.css']
})
export class HistoryExaminationsComponent implements OnInit {
  public history: AppointmentUpdate | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
