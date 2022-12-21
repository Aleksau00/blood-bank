import { Component, OnInit } from '@angular/core';
import {Center} from "../model/center.model";
import {Router} from "@angular/router";
import {AppointmentService} from "../services/appointment.service";
import {MatCalendarCellClassFunction} from "@angular/material/datepicker";
import {Appointment} from "../model/appointment.model";

@Component({
  selector: 'app-add-center-appointment',
  templateUrl: './add-center-appointment.component.html',
  styleUrls: ['./add-center-appointment.component.css']
})
export class AddCenterAppointmentComponent implements OnInit {

  public appointment: Appointment = new Appointment();

  ngOnInit(): void {
  }

  constructor(private appointmentService: AppointmentService, private router: Router) {
  }

  public addAppointment() {
    if (!this.isValidInput()) {
      alert("Fields cannot be empty.");
      return;
    }
    try {
      this.appointmentService.saveCenterAppointment(this.appointment).subscribe(res => {
        alert("Appointment submitted.")
      });
    } catch (error) {
      alert(error)
    }
  }
  public isValidInput(): boolean {
    return (this.appointment.duration != 0)
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
