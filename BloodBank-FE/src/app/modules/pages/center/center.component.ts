import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Center } from '../../bank/model/center.model';
import { Staff } from '../../bank/model/staffCenter.model';
import { UserToken } from '../../bank/model/user-token.model';
import { CenterService } from '../../bank/services/center.service';
import { StaffService } from '../../bank/services/staff.service';
import { TokenStorageService } from '../../bank/services/token-storage.service';

@Component({
  selector: 'app-center',
  templateUrl: './center.component.html',
  styleUrls: ['./center.component.css']
})
export class CenterComponent implements OnInit {

public center: Center | undefined;
public loggedUserToken: UserToken | undefined ;
public loggedUser: Staff = new Staff();


  constructor(private centerService: CenterService, private route: ActivatedRoute, private tokenStorageService: TokenStorageService, private staffService: StaffService) { }

  ngOnInit(): void {

    this.loggedUserToken = this.tokenStorageService.getUser();
    console.log("Ulogovani korisnik: ", this.loggedUserToken)
    if (this.loggedUserToken.role.toString() == 'STAFF') {
      this.staffService.getStaffWithCenter(+this.loggedUserToken.id).subscribe(res => {
        this.loggedUser = res;
        console.log("Ulogovan user (staff): CENTER ", res)
        console.log("CENTER: ", this.loggedUser.center)

        console.log("Center ID: ", this.loggedUser.center.id)
        this.centerService.getCenterById(this.loggedUser.center.id).subscribe(res => {
          this.center = res;
        })
      })
    
    }
 
   
  }

}
