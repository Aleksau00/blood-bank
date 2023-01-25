import { Component, OnInit } from '@angular/core';
import {RegisteredUser} from "../../bank/model/registeredUser.model";
import {RegisteredUserService} from "../../bank/services/registered-user.service";
import {CreateRegisteredUser} from "../../bank/model/createRegisteredUser.model";
import {MatDialog} from "@angular/material/dialog";
import {Router} from "@angular/router";
import {CreatePollComponent} from "../../bank/create-poll/create-poll.component";
import {CreateAddressComponent} from "../../bank/create-address/create-address.component";
import {catchError} from "rxjs";
import {errorContext} from "rxjs/internal/util/errorContext";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  public user: CreateRegisteredUser = new CreateRegisteredUser();
  public confirmPassword: string = '';

  constructor(public dialog: MatDialog, private registeredUserService: RegisteredUserService, private router: Router) { }

  public createUser() {
   // if(!this.isValidInput()) {
     // alert("You must enter all the fields")
     // return;
  //  }
    try {
      this.registeredUserService.createUser(this.user).subscribe(res => {
        alert("Registered successfully");
        this.router.navigate(['']);
      },
        error => {
          catchError(error)
            alert(error)
        });
    } catch (error) {
      console.log("Xdpls");
    }
  }

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateAddressComponent, {
      width: '280px',
      data: {
        address: {
          country: this.user.address.country,
          city: this.user.address.city,
          street: this.user.address.street,
          number: this.user.address.number,
          postalCode: this.user.address.postalCode

        }
      }
    });

    dialogRef.afterClosed().subscribe(result => {
      this.user.address = result.data;
      console.log(this.user.address)
    })
  }

  public isValidInput(): boolean {
      return (this.user.firstName != '' && this.user.password == this.confirmPassword && this.user.lastName != '' && this.user.phoneNumber != '' &&
      this.user.umcn != '' && this.user.institution != '' && this.user.username != '' && this.user.address.country != ''
      && this.user.address.city != '' && this.user.address.street != '' && this.user.address.postalCode != '' && this.user.address.number != '')
  }

  public cancel(): void {
    this.router.navigate(['']);
  }



  ngOnInit(): void {
  }

}
