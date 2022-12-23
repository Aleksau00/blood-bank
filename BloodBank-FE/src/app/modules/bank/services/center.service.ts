import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Center } from "../model/center.model";
import {Method} from "../../pages/home/home.component";
import {Appointment} from "../model/appointment.model";

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  // saveCentar(center: Omit<Centar, "id">): Observable<any>{
  //   return  this.http.post<any>(this.apiHost + 'api/centers', center, {headers: this.headers});
  // }


  saveCenter(center: any): Observable<any>{
    return  this.http.post<any>(this.apiHost + 'api/centers', center, {headers: this.headers});
  }

  getCenters(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiHost + 'api/centers/all', {headers: this.headers});
  }

  getCentersSorted(method: Method): Observable<Center[]> {
    console.log(method);
    return this.http.get<Center[]>(this.apiHost + 'api/centers/all'+method, {headers: this.headers});
  }

  getAppCentersSorted(method: Method, app: Appointment): Observable<Center[]> {
    console.log(method);
    return this.http.post<Center[]>(this.apiHost + 'api/centers/appCenters'+method, app, {headers: this.headers});
  }

  getCentersNameAsc(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiHost + 'api/centers/allNameAsc', {headers: this.headers});
  }

  getCenterById(id: number): Observable<Center> {
    return this.http.get<Center>(this.apiHost + 'api/centers/' + id, {headers: this.headers});
  }

  updateCenter(center: any): Observable<any> {
    return this.http.put<any>(this.apiHost + 'api/centers/' + center.id, center, {headers: this.headers});
  }

  getCenterSuggestions(app: Appointment): Observable<Center[]> {
    return this.http.post<Center[]>(this.apiHost+'api/centers/available-centers', app, {headers: this.headers});
  }

  getCenterAppointment(): Observable<any> {
    return this.http.get<any>(this.apiHost + 'api/centers/center-appointment', {headers: this.headers});
  }
}
