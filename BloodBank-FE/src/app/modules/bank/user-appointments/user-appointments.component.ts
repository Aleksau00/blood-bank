import {Component, Input, OnInit} from '@angular/core';
import {Center} from "../model/center.model";
import {Router} from "@angular/router";
import {CenterService} from "../services/center.service";
import {Appointment} from "../model/appointment.model";
import {AppointmentService} from "../services/appointment.service";
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-user-appointments',
  templateUrl: './user-appointments.component.html',
  styleUrls: ['./user-appointments.component.css']
})
export class UserAppointmentsComponent implements OnInit {

  @Input() appointments :Appointment[]=[]

  displayedColumns: string[] = ['Date','Time', 'Cancel'];


  constructor(public tokenStorageService: TokenStorageService, private centerService: CenterService, private appointmentService: AppointmentService, private router: Router) {
  }


  ngOnInit(): void {

    this.appointmentService.getAppointmentsByUserId(this.tokenStorageService.getUser().id).subscribe(res => {
      this.appointments = res;
    });
  }


  public cancelAppointment(id: number) {
    this.appointmentService.cancelAppointment(id).subscribe( res =>
    {
      alert("Success!")
    }, error => {
      alert("Unable to delete appointment")
    })

  }
}
