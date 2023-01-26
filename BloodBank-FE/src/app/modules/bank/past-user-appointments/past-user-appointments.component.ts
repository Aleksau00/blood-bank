import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../model/center.model";
import {Router} from "@angular/router";
import {CenterService} from "../services/center.service";
import {Appointment} from "../model/appointment.model";
import {AppointmentService} from "../services/appointment.service";
import {TokenStorageService} from "../services/token-storage.service";
import {Method} from "../../pages/home/home.component";

@Component({
  selector: 'app-past-user-appointments',
  templateUrl: './past-user-appointments.component.html',
  styleUrls: ['./past-user-appointments.component.css']
})
export class PastUserAppointmentsComponent implements OnInit {

  @Input() appointments :Appointment[]=[]

  displayedColumns: string[] = ['Date','Time'];
  methods: Method[] = [
    {value: 'DurationAsc', viewValue: 'Duration - MIN first'},
    {value: 'DurationDesc', viewValue: 'Duration - MAX first'},
    {value: 'DateAsc', viewValue: 'Date - MIN first'},
    {value: 'DateDesc', viewValue: 'Date - MAX first'}

  ];
  method: Method = {value: 'DurationAsc', viewValue: 'Duration - Min first'};

  constructor(public tokenStorageService: TokenStorageService, private centerService: CenterService, private appointmentService: AppointmentService, private router: Router) {
  }


  ngOnInit(): void {

    this.initAppointments()
  }

  canCancel(date: Date): boolean{
    if(date == new Date()){
      return false;
    }
    return true;
  }

  public initAppointments(): void {
    this.appointmentService.getPastAppointmentsByUserId(this.tokenStorageService.getUser().id).subscribe(res => {
      this.appointments = res;
    });
  }

  public sortAppointments(newValue : Method) {
    this.method = newValue;
    this.appointmentService.getAppointmentsSorted(this.method, this.tokenStorageService.getUser().id).subscribe(res => {
      this.appointments = res;
    })
  }

}
