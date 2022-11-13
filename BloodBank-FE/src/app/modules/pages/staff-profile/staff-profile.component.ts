import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Staff } from '../../bank/model/staff.model';
import { StaffService } from '../../bank/services/staff.service';

@Component({
  selector: 'app-staff-profile',
  templateUrl: './staff-profile.component.html',
  styleUrls: ['./staff-profile.component.css']
})
export class StaffProfileComponent implements OnInit {
editUser() {
throw new Error('Method not implemented.');
}
createUser() {
throw new Error('Method not implemented.');
}

  public staff: Staff | undefined;

  constructor(private staffService: StaffService, private route: ActivatedRoute) { }

  ngOnInit(): void {
    this.staffService.getStaff(4).subscribe(res => {
    this.staff = res;
    })
  }

}
