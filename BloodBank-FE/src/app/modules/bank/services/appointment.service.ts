import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "../model/appointment.model";

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

  saveCenterAppointment(appointment: any): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/appointments', appointment, {headers: this.headers});
  }


}
