import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment, AppointmentUpdate} from "../model/appointment.model";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService{
  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient){}
  getAppointments(): Observable<Appointment[]> {
    return this.http.get<Appointment[]> (this.apiHost + 'api/appointments/all', {headers: this.headers});

  }

  getAllByUser(id: number): Observable<Appointment[]>{
    return this.http.get<Appointment[]>(this.apiHost + 'api/appointments/users/' + id, {headers: this.headers});
  }
  
  report(updatedAppointment: AppointmentUpdate): Observable<any>{
    return  this.http.post<any>(this.apiHost + 'api/appointments/report', updatedAppointment, {headers: this.headers});
  }

  isURegisteredUserAbleToDonateBlood(id: number): Observable<boolean>{
    return this.http.get<boolean>(this.apiHost + 'api/appointments/poll-result/' + id, {headers: this.headers});
  }
}
