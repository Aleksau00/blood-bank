import {Injectable} from "@angular/core";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {AppUser} from "../model/appUser.model";

@Injectable({
  providedIn: 'root'
})

export class AppUserService {
  currentId : string = ""
  userId: BehaviorSubject<string>;
  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {
    this.userId = new BehaviorSubject<string>(this.currentId);
  }

  getAppUsers(): Observable<AppUser[]>{
    return this.http.get<AppUser[]>(this.apiHost + 'api/users/all', {headers: this.headers});
  }

  gainUser(id: string){
    this.userId.next(this.currentId = id)
  }
}
