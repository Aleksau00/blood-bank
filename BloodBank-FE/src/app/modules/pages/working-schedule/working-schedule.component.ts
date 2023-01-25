import { Component, OnInit } from '@angular/core';
import {View, EventSettingsModel} from "@syncfusion/ej2-angular-schedule";
import {DataManager, WebApiAdaptor} from "@syncfusion/ej2-data";
import {AppointmentService} from "../../bank/services/appointment.service";
import {Router} from "@angular/router";
import {Appointment} from "../../bank/model/appointment.model";
import {publish} from "rxjs";
import {AppointmentDTO} from "../../bank/dto/appointmentDTO.model";

@Component({
  selector: 'app-working-schedule',
  template: '<ejs-schedule [eventSettings]="eventObject" [selectedDate]="setDate"> </ejs-schedule>',
  //templateUrl: './working-schedule.component.html',
  styleUrls: ['./working-schedule.component.css']
})
export class WorkingScheduleComponent implements OnInit {
  public appointments: AppointmentDTO[] = [];
  constructor(private appointmentService: AppointmentService, private router: Router) {
    this.appointmentService.getAppointments().subscribe(res =>{
      this.appointments = res;
      console.log(this.appointments);
    })
  }
  public setDate: Date = new Date(2022,11,23);
  //public eventObject: EventSettingsModel = {}
  public eventObject: EventSettingsModel = {
    dataSource: this.appointments
  }






  ngOnInit(): void {
  }
}
