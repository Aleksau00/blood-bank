import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ActivatedRoute, Params, Router} from "@angular/router";
import {RegisteredUser} from "../../bank/model/registeredUser.model";
import {RegisteredUserService} from "../../bank/services/registered-user.service";
import {TokenStorageService} from "../../bank/services/token-storage.service";

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


  constructor(private tokenStorageService: TokenStorageService, private registerUserService: RegisteredUserService, private router: Router) {}

  ngOnInit(): void {
    this.registerUserService.getUser(this.tokenStorageService.getUser().id, this.tokenStorageService.getToken()!).subscribe(res => {
      console.log(res);
    this.registeredUser = res;
    })
  }

  public editProfile(): void {
    this.router.navigate(['/profile/update'])
  }


}
