import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup} from "@angular/forms";
import {TokenStorageService} from "../../bank/services/token-storage.service";
import {Router} from "@angular/router";
import {AppUserService} from "../../bank/services/appUser.service";
import {LoginRequest} from "../../bank/model/loginRequest.model";
import {AuthService} from "../../bank/services/auth.service";

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  loginForm = new FormGroup({
    email: new FormControl<string | undefined>(undefined),
    password: new FormControl<string | undefined>(undefined)
  })

  constructor(private authService: AuthService, private tokenStorageService:TokenStorageService, private router:Router, private appUserService: AppUserService) { }

  ngOnInit(): void {
  }

  public signIn() {
    const loginRequest:LoginRequest = new LoginRequest({
      email: this.loginForm.controls.email.value!,
      password: this.loginForm.controls.password.value!,
    })
    console.log(loginRequest);
    this.authService.authenticate(loginRequest).subscribe({
        next: response => {
          console.log("X")
          console.log(response)
          this.tokenStorageService.saveToken(response)
          this.tokenStorageService.saveUser(response.accessToken)
          alert("Success!");
          this.router.navigate(['']).then(
            ()=>{
              window.location.reload();
            }
          );
        },
        error: message => {
          console.log(message.Error)
          alert("Boom!Error!")
        }

      }
    )
  }

}
