import { Component, OnInit } from '@angular/core';
import {MatDialog} from "@angular/material/dialog";
import {CreateCenterComponent} from "../../bank/create-center/create-center.component";

@Component({
  selector: 'app-system-administrator',
  templateUrl: './system-administrator.component.html',
  styleUrls: ['./system-administrator.component.css']
})
export class SystemAdministratorComponent implements OnInit {



  constructor(public dialog: MatDialog) { }

  openDialog(): void {
    const dialogRef = this.dialog.open(CreateCenterComponent, {
      width: '400px',
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log('The dialog was closed');
    });
  }

  ngOnInit(): void {
  }

}
