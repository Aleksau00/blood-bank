import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {Admin} from "../model/admin.model";

@Injectable({
  providedIn: 'root'
})

export class AdminService {
  currentId : string = ""
  userId: BehaviorSubject<string>;
  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {
    this.userId = new BehaviorSubject<string>(this.currentId);
  }

  getAdmin(id: number): Observable<Admin> {
    return this.http.get<Admin>(this.apiHost + 'api/admins/' + id, {headers: this.headers});
  }
  updateAdmin(admin: any): Observable<any> {
    admin.firstLogin = false;
    admin.isFirstLogin = false;
    return this.http.put<any>(this.apiHost + 'api/admins/' + admin.id, admin, {headers: this.headers});
  }

}
