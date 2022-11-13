import { Component, OnInit } from '@angular/core';
import {Center} from "../../bank/model/center.model";
import {CenterService} from "../../bank/services/center.service";
import {Router} from "@angular/router";
import {Staff} from "../../bank/model/staffCenter.model";
import {StaffService} from "../../bank/services/staff.service";

@Component({
  selector: 'app-system-administrator',
  templateUrl: './system-administrator.component.html',
  styleUrls: ['./system-administrator.component.css']
})
export class SystemAdministratorComponent implements OnInit {

  public staff: Staff = new Staff();
  public center: Center = new Center();
  public consent: boolean = false;


  constructor(private centerService: CenterService,private staffService: StaffService, private router: Router) {
  }


  public createCenter() {
    if (!this.isValidInput()) {
      alert("Center cannot be empty.");
      return;
    }
    try {
      this.centerService.saveCenter(this.center).subscribe(res => {
        alert("Poll submitted.")
      });
    } catch (error) {
      alert(error)
    }
  }

  public createStaff(){
    if (!this.isValidInput()) {
      alert("Staff cannot be empty.");
      return;
    }
    try {
      this.staffService.saveStaff(this.staff).subscribe(res => {
        alert("Staff submitted.")
      });
    } catch (error) {
      alert(error)
    }
  }

  public isValidInput(): boolean {
    return (this.staff.center.name != '' && this.staff.center.address.country != '' &&
      this.staff.center.address.city != '' && this.staff.center.address.street != '' &&
      this.staff.center.address.number != '' && this.staff.center.address.postalCode != '' &&
      this.staff.center.averageGrade != null && this.staff.center.startTime != '' &&
      this.staff.center.endTime != '' && this.staff.center.description != '' &&
      this.staff.username != '' && this.staff.password != '' &&
      this.staff.firstName != '' && this.staff.lastName != '' &&
      this.staff.email != '' && this.staff.address.country != '' &&
      this.staff.address.city != '' && this.staff.address.street != '' &&
      this.staff.address.number != '' && this.staff.address.postalCode != '' &&
      this.staff.phoneNumber != '' && this.staff.umcn != '' &&
      this.staff.institution != '' && this.staff.gender != '')
  }

  ngOnInit(): void {
  }

}
