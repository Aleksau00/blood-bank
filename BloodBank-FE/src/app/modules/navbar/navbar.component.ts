import { Component, OnInit } from '@angular/core';
import { CreatePollComponent } from "../bank/create-poll/create-poll.component";
import {MatDialog} from "@angular/material/dialog";
import {RegistrationComponent} from "../pages/registration/registration.component";
import {Router} from "@angular/router";
import {AppUser} from "../bank/model/appUser.model";
import {TokenStorageService} from "../bank/services/token-storage.service";
import {UserToken} from "../bank/model/user-token.model";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  user: UserToken;
  role: any;
  isLoggedIn: boolean = false;

  constructor(private tokenStorageService: TokenStorageService, public dialog: MatDialog, private router: Router) {
    this.user = this.tokenStorageService.getUser()
    this.isLoggedIn = this.tokenStorageService.isLoggedIn()
  }

  openDialogCreatePoll(): void {
    const dialogRef = this.dialog.open(CreatePollComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    })
  }

  openRegistration(): void {
    this.router.navigate(['/registration']);
  }

  openLogin(): void {
    this.router.navigate(['/login']);
  }

  openHome(): void {
    this.router.navigate(['']);
  }

  AddUserAppointment(): void {
    this.router.navigate(['/add-user-appointment']);
  }

  AddAppointment(): void {
    this.router.navigate(['/add-center-appointment']);
  }

  AccountInfo(): void {
    this.router.navigate(['/profile']);
  }

  UserAppointments(): void {
    this.router.navigate(['/user-appointments']);
  }
  ngOnInit(): void {
  }

  isRegisteredUser(): boolean{
    if(this.tokenStorageService.getUser().role.toString() == 'USER'){
      return true;
    }
    return false;
  }

  isStaff(): boolean{
    if(this.tokenStorageService.getUser().role.toString() == 'STAFF'){
      return true;
    }
    return false;
  }

  isAdmin(): boolean{
    if(this.tokenStorageService.getUser().role.toString() == 'ADMIN'){
      return true;
    }
    return false;
  }

  signOut() {
    this.tokenStorageService.signOut()
    this.router.navigate(['']).then(()=>{
      window.location.reload();
    })
  }

  addCenter() {
    this.router.navigate(['/system-administrator']);
  }
}
