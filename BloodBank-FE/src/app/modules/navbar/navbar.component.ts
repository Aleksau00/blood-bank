import { Component, OnInit } from '@angular/core';
import { CreatePollComponent } from "../bank/create-poll/create-poll.component";
import {MatDialog} from "@angular/material/dialog";
import {RegistrationComponent} from "../pages/registration/registration.component";
import {Router} from "@angular/router";

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.css']
})
export class NavbarComponent implements OnInit {

  constructor(public dialog: MatDialog, private router: Router) { }

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

  ngOnInit(): void {
  }

}
