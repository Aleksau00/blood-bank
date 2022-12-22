import { Component, OnInit } from '@angular/core';
import {Staff} from "../../bank/model/staff.model";
import {StaffService} from "../../bank/services/staff.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-change-password',
  templateUrl: './change-password.component.html',
  styleUrls: ['./change-password.component.css']
})
export class ChangePasswordComponent implements OnInit {

  public staff: Staff | undefined;

  constructor(private staffService: StaffService, private route: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.staffService.getStaff(5).subscribe(res => {
      this.staff = res;
    })
  }

  updateStaff(): void {
    this.staffService.updateStaff(this.staff).subscribe(res => {
      this.router.navigate(['/staff-profile']);
    });
  }

}
