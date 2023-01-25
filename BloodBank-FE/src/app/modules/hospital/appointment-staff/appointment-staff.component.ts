import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from '../../bank/model/appointment.model';
import { AppointmentUpdate } from '../../bank/model/appointmentUpdate.model';
import { AppointmentService } from '../../bank/services/appointment.service';

@Component({
  selector: 'app-appointment-staff',
  templateUrl: './appointment-staff.component.html',
  styleUrls: ['./appointment-staff.component.css']
})
export class AppointmentStaffComponent implements OnInit {
  displayedColumns: string[] = ['firstName', 'lastName', 'dateTime', 'duration'];
  dataSource: Appointment[] = [];
  clickedRows = new Set<Appointment>();
  selectedAppointment: Appointment = new Appointment();
  activeDiv: number = 0;
  checkedQ1: boolean = false;
  checkedQ2: boolean = false;
  newAppointment: AppointmentUpdate = new AppointmentUpdate;
  canDonateBlood: string = "";

  constructor(private readonly router1: Router, private readonly router: ActivatedRoute, private appointmentService: AppointmentService) { }

  ngOnInit(): void {
    this.appointmentService.getAllByUser(1).subscribe(res => {
      this.dataSource = res;
      console.log(res)
    })


  }

  getSelectedRow(row: Appointment) {
    console.log(row.registeredUserDTO.lastName)
    this.selectedAppointment = row;

    this.newAppointment.id = this.selectedAppointment.id;
    this.newAppointment.dateTime = this.selectedAppointment.dateTime;
    this.newAppointment.duration = this.selectedAppointment.duration;
    this.newAppointment.registeredUserDTO = this.selectedAppointment.registeredUserDTO;
  }

  redirectToQuery1() {
    ++this.activeDiv;
  }

  redirectToQuery2() {
    console.log("checkedQ1 ", this.checkedQ1)

    if (this.checkedQ1) {
      this.newAppointment.status = 0; // CANELED

      console.log("New Appointment ", this.newAppointment)
      this.appointmentService.report(this.newAppointment).subscribe({
        next: res => {
          this.router1.navigateByUrl('');
        }
      })
    } else {
      ++this.activeDiv;

      this.appointmentService.isURegisteredUserAbleToDonateBlood(2).subscribe(res => {
        
        this.canDonateBlood = res ? "YES" : "NO";
        console.log(this.canDonateBlood)
        console.log(res)
      })
    }
  }

  redirectToForm() {
    console.log("checkedQ2 ", this.checkedQ2)


    if (this.checkedQ2) {
      this.newAppointment.status = 2; // DENIED

      console.log("New Appointment ", this.newAppointment)
      this.appointmentService.report(this.newAppointment).subscribe({
        next: res => {
          this.router1.navigateByUrl('');
        }
      })
    } else {
      ++this.activeDiv;
    }
  }

  onSubmit() {

    this.newAppointment.status = 1; // FINISHED
    this.newAppointment.blood.id = 1;
    this.newAppointment.blood.type = 0;

    console.log("New Appointment ", this.newAppointment)
    this.appointmentService.report(this.newAppointment).subscribe({
      next: res => {
        this.router1.navigateByUrl('');

      }
    })
  }

}
