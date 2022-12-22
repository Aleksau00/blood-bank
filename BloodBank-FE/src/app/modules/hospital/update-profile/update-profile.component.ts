import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router, Params } from "@angular/router";
import { RegisteredUser} from "../../bank/model/registeredUser.model";
import { RegisteredUserService} from "../../bank/services/registered-user.service";
import {TokenStorageService} from "../../bank/services/token-storage.service";

@Component({
  selector: 'de-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})

export class UpdateProfileComponent implements OnInit {

  public registeredUser: RegisteredUser | undefined = undefined;

  constructor(private tokenStorageService: TokenStorageService, private registeredUserService: RegisteredUserService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
      this.registeredUserService.getUser(this.tokenStorageService.getUser().id, this.tokenStorageService.getToken()!).subscribe(res => {
        this.registeredUser = res;
      })
  }

  public updateUser(): void {
    if (!this.isValidInput()) return;
    this.registeredUserService.updateUser(this.registeredUser).subscribe(res => {
      this.router.navigate(['/profile']);
    });
  }

  public updatePassword(): void {
    if (!this.isValidInput()) return;
    this.registeredUserService.updatePassword(this.registeredUser).subscribe(res => {
      this.router.navigate(['/profile']);
    });
  }

  private isValidInput(): boolean {
    return this.registeredUser?.firstName != '' && this.registeredUser?.lastName != ''
      && this.registeredUser?.gender != '' && this.registeredUser?.umcn != ''
      && this.registeredUser?.address?.city != '' && this.registeredUser?.address?.country != ''
      && this.registeredUser?.address?.postalCode != '' && this.registeredUser?.address?.street != ''
      && this.registeredUser?.address?.number != '' && this.registeredUser?.username != ''
      && this.registeredUser?.email != '' && this.registeredUser?.phoneNumber != ''
      && this.registeredUser?.institution != '';
  }

  public cancel(): void {
    this.router.navigate(['/profile'])
  }
}
