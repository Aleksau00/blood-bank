import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {RegisteredUser} from "../../bank/model/registeredUser.model";
import {RegisteredUserService} from "../../bank/services/registered-user.service";

export interface Method{
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {

  public registeredUser: RegisteredUser | undefined;

  constructor(private registerUserService: RegisteredUserService, private route: ActivatedRoute) {}

  ngOnInit(): void {
    this.registerUserService.getUser(1).subscribe(res => {
      console.log(res);
    this.registeredUser = res;
    })
  }

}
