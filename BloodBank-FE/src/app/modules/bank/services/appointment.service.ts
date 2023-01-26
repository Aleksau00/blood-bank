import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Appointment} from "../model/appointment.model";
import {AppointmentDTO} from "../dto/appointmentDTO.model";
import {Center} from "../model/center.model";
import LocalTime from "ts-time/LocalTime";
import LocalDate from "ts-time/LocalDate";
import {Method} from "../../pages/home/home.component";
import { AppointmentUpdate } from "../model/appointmentUpdate.model";


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

  saveUserAppointment(appointment: any, id: number): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/appointments/userAppointment/' + id, appointment, {headers: this.headers});
  }




  getCenterAppointment(id: number, date: LocalDate, time: LocalTime): Observable<Appointment> {
    return this.http.get<Appointment>(this.apiHost + 'api/appointments/centers/' + id, {headers: this.headers});
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
  getPredefinedAppointmentsForCenter(id: number, sort: Method): Observable<AppointmentUpdate[]> {
    return this.http.get<AppointmentUpdate[]> (this.apiHost + 'api/appointments/predefined/'+sort+'/'+id, {headers: this.headers});

  }


  getPredefinedAppointmentsForCenterInit(id: number, sort: string): Observable<AppointmentUpdate[]> {
    return this.http.get<AppointmentUpdate[]> (this.apiHost + 'api/appointments/predefined/'+sort+'/'+id, {headers: this.headers});

  }

  bookAppointment(appointment: any, id: number): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/predefined/' + id, appointment, {headers: this.headers});
  }

  getWantedAppointment(appointment: any, id: number): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/appointments/wanted/' + id , appointment, {headers: this.headers});
  }

  getAppointmentsByUserId(id: number): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/appointments/byUserFuture/' + id, {headers: this.headers});
  }

  getPastAppointmentsByUserId(id: number): Observable<any>{
    return this.http.post<any>(this.apiHost + 'api/appointments/byUserPast/' + id,  {headers: this.headers});
  }

  cancelAppointment(id: number): Observable<any>{
    return this.http.put<any>(this.apiHost + 'api/appointments/cancel/' + id, {headers: this.headers});
  }

  getAppointmentsSorted(method: Method, id: number): Observable<Appointment[]> {
    console.log(method);
    return this.http.get<Appointment[]>(this.apiHost + 'api/appointments/all'+method+'/'+ id, {headers: this.headers});
  }
}
