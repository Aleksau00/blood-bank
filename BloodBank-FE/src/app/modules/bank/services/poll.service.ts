import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import { Poll } from "../model/poll.model";

@Injectable({
  providedIn: 'root'
})
export class PollService {

  apiHost: string = 'http://localhost:8082/';
  headers: HttpHeaders = new HttpHeaders({ 'Content-Type': 'application/json' });

  constructor(private http: HttpClient) { }

  createPoll(poll: any): Observable<any> {
    return this.http.post<any>(this.apiHost + 'api/polls', poll, {headers: this.headers});
  }

}
