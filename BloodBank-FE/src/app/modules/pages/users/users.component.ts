import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {AppUserService} from "../../bank/services/appUser.service";
import {Router} from "@angular/router";
import { AppUser } from "../../bank/model/appUser.model";
import {Center} from "../../bank/model/center.model";

export interface Method{
  value: string;
  viewValue: string;
}

@Component({
  selector: 'app-users',
  templateUrl: './users.component.html',
  styleUrls: ['./users.component.css']
})
export class UsersComponent implements OnInit {

  public users: AppUser[] = [];

  constructor(private appUserService: AppUserService, private router: Router) {}

  ngOnInit(): void{
    this.appUserService.getAppUsers().subscribe(res => {
      this.users = res;
      console.log(this.users);
    })
  }

  searchText: string = '';

  onSearchTextEntered(searchValue: string){
    this.searchText = searchValue;
  }

}
