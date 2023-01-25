import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {AppointmentService} from "../services/appointment.service";
import {MatCalendarCellClassFunction} from "@angular/material/datepicker";
import {TokenStorageService} from "../services/token-storage.service";
import {Overlay, ScrollStrategy, ScrollStrategyOptions} from "@angular/cdk/overlay";
import {MatDialog} from "@angular/material/dialog";
import {
  AvailableCentersForAppointments
} from "../available-centers-for-appointment/available-centers-for-appointment.component";
import { AppointmentUpdate } from '../model/appointmentUpdate.model';

@Component({
  selector: 'app-add-user-appointment',
  templateUrl: './add-user-appointment.component.html',
  styleUrls: ['./add-user-appointment.component.css']
})
export class AddUserAppointmentComponent implements OnInit {

  public appointment: AppointmentUpdate = new AppointmentUpdate();
  scrollStrategy: ScrollStrategy;

  constructor(private dialog: MatDialog, private appointmentService: AppointmentService, private router: Router, public tokenStorageService: TokenStorageService, private overlay: Overlay, private readonly sso: ScrollStrategyOptions) {
    this.scrollStrategy =   this.sso.noop();
  }

  ngOnInit(): void {
  }

  openDialog(): void {
    const scrollStrategy = this.overlay.scrollStrategies.reposition();
    const dialogRef = this.dialog.open(AvailableCentersForAppointments, {
      scrollStrategy: scrollStrategy,
      width: '800px',
      data: {
        date: this.appointment.date,
        time: this.appointment.time,
        registeredUserDTO: this.tokenStorageService.getUser().id
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      if (result == 1) {
        //this.toast.error({detail: 'Try again', summary: "Appointment is not available anymore!", duration: 5000})
      } else if (result == 0) {
        //this.toast.success({detail: 'Successful', summary: "Appointment is scheduled!", duration: 5000})
      } else if (result == 2) {
        //this.toast.error({detail: 'Unavailable', summary: "!", duration: 5000})
      }
    });
  }

  dateClass: MatCalendarCellClassFunction<Date> = (cellDate, view) => {
    // Only highligh dates inside the month view.
    if (view === 'month') {
      const date = cellDate.getDate();

      // Highlight the 1st and 20th day of each month.
      return date === 1 || date === 20 ? 'example-custom-date-class' : '';
    }

    return '';
  };

}
