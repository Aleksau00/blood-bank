import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "../model/appointment.model";
import {AppointmentDTO} from "../dto/appointmentDTO.model";

@Injectable({
  providedIn: 'root'
})
export class AppointmentService{
  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({'Content-Type': 'application/json'});

  constructor(private http: HttpClient){}
  getAppointments(): Observable<AppointmentDTO[]> {
    return this.http.get<AppointmentDTO[]> (this.apiHost + 'api/appointments/all', {headers: this.headers});

  }

  saveCenterAppointment(appointment: any, id: number): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/appointments/' + id, appointment, {headers: this.headers});
  }


}
