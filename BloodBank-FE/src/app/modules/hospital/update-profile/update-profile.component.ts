import { Component, OnInit } from "@angular/core";
import { ActivatedRoute, Router, Params } from "@angular/router";
import { RegisteredUser} from "../../bank/model/registeredUser.model";
import { RegisteredUserService} from "../../bank/services/registered-user.service";

@Component({
  selector: 'de-update-profile',
  templateUrl: './update-profile.component.html',
  styleUrls: ['./update-profile.component.css']
})

export class UpdateProfileComponent implements OnInit {

  public registeredUser: RegisteredUser | undefined = undefined;

  constructor(private registeredUserService: RegisteredUserService, private route: ActivatedRoute,
    private router: Router) { }

  ngOnInit(): void {
      this.registeredUserService.getUser(1).subscribe(res => {
        this.registeredUser = res;
      })
  }

  public updateUser(): void {
    if (!this.isValidInput()) return;
    this.registeredUserService.updateUser(this.registeredUser).subscribe(res => {
      this.router.navigate(['/profile/update']);
    });
  }

  private isValidInput(): boolean {
    return this.registeredUser?.firstName != '' && this.registeredUser?.lastName != '';
  }
}
