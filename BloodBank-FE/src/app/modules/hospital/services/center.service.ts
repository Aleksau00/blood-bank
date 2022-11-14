import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";
import {Center} from "../../bank/model/center.model";

@Injectable({
  providedIn: 'root'
})
export class CenterService{
  [x: string]: any;
  apiHost: string = 'http://localhost:5000';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) {}

  getCenters(): Observable<Center[]> {
    return this.http.get<Center[]>(this.apiHost + 'api/centers', {headers: this.headers});
  }
}
