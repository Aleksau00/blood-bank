import {Component, Inject, OnInit} from '@angular/core';
import {TokenStorageService} from "../services/token-storage.service";
import {CenterService} from "../services/center.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {AppointmentService} from "../services/appointment.service";
import {Center} from "../model/center.model";
import {Appointment} from "../model/appointment.model";
import {Method} from "../../pages/home/home.component";

@Component({
  selector: 'app-center-view',
  templateUrl: './center-view.component.html',
  styleUrls: ['./center-view.component.css']
})
export class CenterViewComponent implements OnInit {
  Date: any;
  Time: any;
  appointments: Appointment[] = [];

  methods: Method[] = [
    {value: 'Date', viewValue: 'Date'},
    {value: 'Time', viewValue: 'Time'},
  ];
  method: Method = {value: 'Date', viewValue: 'Date'};

  constructor(private appointmentService: AppointmentService,private tokenStorageService: TokenStorageService, private centerService: CenterService, public dialogRef: MatDialogRef<CenterViewComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.appointmentService.getPredefinedAppointmentsForCenterInit(this.data.centerId, "Date").subscribe(
      res=> {
        this.appointments = res;
        console.log(this.appointments)
        console.log(this.data.centerId)
      }
    );
  }

  public sortAppointments(newValue : Method) {
    this.method = newValue;
    this.appointmentService.getPredefinedAppointmentsForCenter(this.data.centerId, this.method).subscribe(res => {
      this.appointments = res;
    })
  }

  public bookAppointment(appointment: Appointment) {
    this.appointmentService.bookAppointment(appointment, this.tokenStorageService.getUser().id).subscribe(
      res => {
        console.log(res);
        alert("Success");
        this.dialogRef.close()
      },
      error => {
        alert("Poll not filled/updated in last 6 months")
      }
    )

  }
}
