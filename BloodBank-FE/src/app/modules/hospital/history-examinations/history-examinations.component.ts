import { Component, OnInit } from '@angular/core';
import { Appointment } from '../../bank/model/appointment.model';
import { AppointmentHistory } from '../../bank/model/appointmentHistory.model';
import { AppointmentUpdate } from '../../bank/model/appointmentUpdate.model';
import { UserToken } from '../../bank/model/user-token.model';
import { AppointmentService } from '../../bank/services/appointment.service';
import { TokenStorageService } from '../../bank/services/token-storage.service';


@Component({
  selector: 'app-history-examinations',
  templateUrl: './history-examinations.component.html',
  styleUrls: ['./history-examinations.component.css']
})
export class HistoryExaminationsComponent implements OnInit {
  public history: AppointmentUpdate | undefined;

  loggedUser: UserToken;
  appointments: AppointmentHistory[] =[];

  displayedColumns: string[] = ['Date','Time', 'Duration','Description'];


  constructor(private tokenStorageService: TokenStorageService, private appointmentService: AppointmentService) {
    this.loggedUser = this.tokenStorageService.getUser();
    console.log("Ulogovani korisnik (Registered user): ", this.loggedUser)

   }

  ngOnInit(): void {
    this.appointmentService.getHistoryOfRegisteredUserAppointments(+this.loggedUser.id).subscribe(res => {
      this.appointments = res;
      console.log("Istorija pregleda: ", res)
    })
  }

}
