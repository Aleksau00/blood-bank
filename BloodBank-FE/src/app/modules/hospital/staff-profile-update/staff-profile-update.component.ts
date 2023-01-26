import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Staff } from '../../bank/model/staff.model';
import { UserToken } from '../../bank/model/user-token.model';
import { StaffService } from '../../bank/services/staff.service';
import { TokenStorageService } from '../../bank/services/token-storage.service';

@Component({
  selector: 'app-staff-profile-update',
  templateUrl: './staff-profile-update.component.html',
  styleUrls: ['./staff-profile-update.component.css']
})
export class StaffProfileUpdateComponent implements OnInit {


  public staff: Staff | undefined;
  public loggedUserToken: UserToken | undefined ;

  constructor(private staffService: StaffService, private route: ActivatedRoute, private router: Router,  private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {

    this.loggedUserToken = this.tokenStorageService.getUser();
    console.log("Ulogovani korisnik: ", this.loggedUserToken)
    if (this.loggedUserToken.role.toString() == 'STAFF') {
      this.staffService.getStaff(this.loggedUserToken.id).subscribe(res => {
        this.staff = res;
        })
    }


   
  }

  updateStaff(): void {
    if (!this.isValidInput()) return;
    this.staffService.updateStaff(this.staff).subscribe(res => {
      this.router.navigate(['/staff-profile']);
    });
  }

  private isValidInput(): boolean {
    return this.staff?.firstName != '' && this.staff?.lastName != '';
  }
}
