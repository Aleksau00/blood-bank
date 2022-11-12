import { Component, OnInit } from '@angular/core';
import { CreatePollComponent } from "../bank/create-poll/create-poll.component";
import {MatDialog} from "@angular/material/dialog";
import {RegistrationComponent} from "../pages/registration/registration.component";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public dialog: MatDialog) { }

  openDialogCreatePoll(): void {
    const dialogRef = this.dialog.open(CreatePollComponent, {
      width: '500px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    })
  }

  openDialogRegistration(): void {
    const dialogRef = this.dialog.open(RegistrationComponent, {
      width: '600px',
    });

    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    })
  }

  ngOnInit(): void {
  }

}
