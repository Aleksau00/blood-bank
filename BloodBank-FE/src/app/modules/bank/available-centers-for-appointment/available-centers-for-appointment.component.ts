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
import {MatSelectChange} from "@angular/material/select";
import { AppointmentUpdate } from '../model/appointmentUpdate.model';

export interface Method {
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-available-centers-for-appointment',
  templateUrl: './available-centers-for-appointment.component.html',
  styleUrls: ['./available-centers-for-appointment.component.css']
})
export class AvailableCentersForAppointments implements OnInit {

  methods: Method[] = [
    {value: 'AverageGradeAsc', viewValue: 'Avg. grade 5-0'},
    {value: 'AverageGradeDesc', viewValue: 'Avg. grade 0-5'}
  ];
  method: Method = {value: 'AverageGradeAsc', viewValue: 'Avg. grade 0-5'};

  constructor(public tokenStorageService: TokenStorageService, private appointmentService: AppointmentService, private centerService: CenterService, public dialogRef: MatDialogRef<AvailableCentersForAppointments>,@Inject(MAT_DIALOG_DATA) public data: any, private router: Router) {
  }

  centers: Center[] = [];
  appointment: AppointmentUpdate = new AppointmentUpdate();



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

  public sortCenters() {
    this.centers.sort((a, b) => (a.averageGrade!>b.averageGrade! ? -1 : 1))
  }

  //public sortCentersDesc() {
  //  this.centers.sort((a, b) => (a.averageGrade!<b.averageGrade! ? -1 : 1))
  //}

  public createAppointment(center: any) {
    if (!this.isValidInput()) {
      alert("Fields cannot be empty.");
      return;
    }
    try {
      //this.appointment = this.appointmentService.getCenterAppointment(center.id, this.data.date, this.data.time);
      this.appointment.registeredUserDTO.id = this.tokenStorageService.getUser().id;
      this.appointmentService.getWantedAppointment(this.appointment, center.id).subscribe(res => {
        this.appointment = res;
        //this.dialogRef.close(0);
      });
      this.appointmentService.bookAppointment(this.appointment, this.tokenStorageService.getUser().id).subscribe(res => {
        this.appointment = res;
        this.dialogRef.close(0);
      });
    } catch (error) {
      this.dialogRef.close(1);
    }
  }
  public isValidInput(): boolean {
    return true;
  }

}
