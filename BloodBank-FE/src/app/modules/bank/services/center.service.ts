import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Center } from "../model/center.model";
import {Method} from "../../pages/home/home.component";

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getCenters(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiHost + 'api/centers/all', {headers: this.headers});
  }

  getCentersSorted(method: Method): Observable<Center[]> {
    console.log(method);
    return this.http.get<Center[]>(this.apiHost + 'api/centers/all'+method, {headers: this.headers});
  }

  getCentersNameAsc(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiHost + 'api/centers/allNameAsc', {headers: this.headers});
  }

}
