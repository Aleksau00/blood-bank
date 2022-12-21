import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {StaffService} from "../../bank/services/staff.service";
import {Staff} from "../../bank/model/staffCenter.model";

@Component({
  selector: 'app-add-administrator',
  //template: '<ejs-schedule></ejs-schedule>',
  templateUrl: './add-administrator.component.html',
  styleUrls: ['./add-administrator.component.css']
})
export class AddAdministratorComponent implements OnInit {

  public staff: Staff = new Staff();


  constructor(private staffService: StaffService) {

  }

  public createStaff(){
    try {
      this.staffService.saveStaff(this.staff).subscribe(res => {
        alert("Staff submitted.")
      });
    } catch (error) {
      alert(error)
    }
  }

  ngOnInit(): void {
  }

}
