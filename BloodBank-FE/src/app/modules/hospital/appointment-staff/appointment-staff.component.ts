import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Appointment } from '../../bank/model/appointment.model';
import { AppointmentUpdate } from '../../bank/model/appointmentUpdate.model';
import { Staff } from '../../bank/model/staffCenter.model';
import { UserToken } from '../../bank/model/user-token.model';
import { AppointmentService } from '../../bank/services/appointment.service';
import { StaffService } from '../../bank/services/staff.service';
import { TokenStorageService } from '../../bank/services/token-storage.service';

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
  userId: string = "";
  loggedUserToken: UserToken;
  loggedUser: Staff = new Staff();

  constructor(private tokenStorageService: TokenStorageService,private readonly router1: Router, private readonly router: ActivatedRoute, private appointmentService: AppointmentService, private staffService: StaffService) {
    this.loggedUserToken = this.tokenStorageService.getUser();
   }

  ngOnInit(): void {
    //get staff with center 
    this.staffService.getStaffWithCenter(+this.loggedUserToken.id).subscribe(res => {
      this.loggedUser = res;
      console.log("Ulogovan user (staff): ", res)
    })

    this.userId = window.location.pathname.split('appointment-staff/')[1];
    console.log(this.userId);
    this.appointmentService.getAllByUser(+this.userId).subscribe(res => {
      this.dataSource = res;
      console.log("Selektovani register user: ", res)
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

      this.appointmentService.isURegisteredUserAbleToDonateBlood(+this.userId).subscribe(res => {
        console.log("Donate blood method: ")
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
    this.newAppointment.blood.amount = 5.1;


    console.log("New Appointment ", this.newAppointment)
    this.appointmentService.report(this.newAppointment).subscribe({
      next: res => {
        this.router1.navigateByUrl('');

      }
    })
  }

}
