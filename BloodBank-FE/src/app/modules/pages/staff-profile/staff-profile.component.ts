import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Staff } from '../../bank/model/staff.model';
import { UserToken } from '../../bank/model/user-token.model';
import { StaffService } from '../../bank/services/staff.service';
import { TokenStorageService } from '../../bank/services/token-storage.service';

@Component({
  selector: 'app-staff-profile',
  templateUrl: './staff-profile.component.html',
  styleUrls: ['./staff-profile.component.css']
})
export class StaffProfileComponent implements OnInit {


  public staff: Staff | undefined;
  public loggedUserToken: UserToken | undefined ;


  constructor(private staffService: StaffService, private route: ActivatedRoute, private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {

    this.loggedUserToken = this.tokenStorageService.getUser();
    console.log("Ulogovani korisnik: ", this.loggedUserToken)
    if (this.loggedUserToken.role.toString() == 'STAFF') {
      this.staffService.getStaff(this.loggedUserToken.id).subscribe(res => {
        this.staff = res;
        })
    }



  }

}
