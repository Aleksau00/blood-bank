import {Component, Inject, OnInit} from '@angular/core';
import {Center} from "../model/center.model";
import {Router} from "@angular/router";
import {AppointmentService} from "../services/appointment.service";
import {MatCalendarCellClassFunction} from "@angular/material/datepicker";
import {Appointment} from "../model/appointment.model";
import {MatDialogRef} from "@angular/material/dialog";
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import {CenterService} from "../services/center.service";
import {TokenStorageService} from "../services/token-storage.service";

@Component({
  selector: 'app-available-centers-for-appointment',
  templateUrl: './available-centers-for-appointment.component.html',
  styleUrls: ['./available-centers-for-appointment.component.css']
})
export class AvailableCentersForAppointments implements OnInit {



  constructor(public tokenStorageService: TokenStorageService, private appointmentService: AppointmentService, private centerService: CenterService, public dialogRef: MatDialogRef<AvailableCentersForAppointments>,@Inject(MAT_DIALOG_DATA) public data: any, private router: Router) {
  }

  centers: Center[] = [];
  appointment: Appointment = new Appointment();

  ngOnInit(): void {
    this.appointment.date = this.data.date;
    this.appointment.time = this.data.time;
    this.appointment.registeredUserDTO = this.data.registeredUserDTO;
    this.centerService.getCenterSuggestions(this.appointment).subscribe(
      res => {
        this.centers = res;
      }
    );
  }

  public createAppointment(center: any) {
    if (!this.isValidInput()) {
      alert("Fields cannot be empty.");
      return;
    }
    try {
      this.appointment.registeredUserDTO.id = this.tokenStorageService.getUser().id;
      this.appointment.date = this.appointment.date;
      this.appointment.time = this.appointment.time;
      this.appointmentService.saveCenterAppointment(this.appointment, this.tokenStorageService.getUser().id).subscribe(res => {
        this.dialogRef.close(0);
      });
    } catch (error) {
      this.dialogRef.close(1);
    }
  }
  public isValidInput(): boolean {
    return (this.appointment.duration != 0)
  }

}
