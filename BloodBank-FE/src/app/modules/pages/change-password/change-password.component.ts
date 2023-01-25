import { Component, OnInit } from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {AdminService} from "../../bank/services/admin.service";
import {Admin} from "../../bank/model/admin.model";
import {TokenStorageService} from "../../bank/services/token-storage.service";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public admin: Admin | undefined;

  constructor(private tokenStorageService: TokenStorageService, private adminService: AdminService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.adminService.getAdmin(this.tokenStorageService.getUser().id).subscribe(res => {
      this.admin = res;
      console.log(this.admin)
    })
  }

  updateAdmin(): void {
    this.adminService.updateAdmin(this.admin).subscribe(res => {
      alert("Success!");
      console.log(this.admin)
    });
  }

}
