import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core'; 
import { Observable } from 'rxjs';
import { Staff } from '../model/staff.model';

@Injectable({
  providedIn: 'root'
})
export class StaffService {

  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  getStaff(id: number): Observable<Staff> {
    return this.http.get<Staff>(this.apiHost + 'api/staff/' + id, {headers: this.headers});
  }

  updateStaff(staff: any, id: number): Observable<Staff> {
    return this.http.put<Staff>(this.apiHost + 'api/staff' + id, {this.headers: this.headers})
  }
}
