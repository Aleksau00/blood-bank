import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { RegisteredUser } from "../model/registeredUser.model";
import { Staff } from '../model/staff.model';

@Injectable({
  providedIn: 'root'
})
export class RegisteredUserService {

  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });
 

  constructor(private http: HttpClient) { }

  getUser(id: number): Observable<RegisteredUser> {
    return this.http.get<RegisteredUser>(this.apiHost + 'api/registeredUsers/' + id, {headers: this.headers});
  }

  createUser(user: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/registeredUsers', user, {headers: this.headers});
  }
}
