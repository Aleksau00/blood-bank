import { Component, OnInit } from '@angular/core';
import {Params, Router} from "@angular/router";
import { Poll } from "../model/poll.model";
import { RegisteredUser } from "../model/registeredUser.model";
import { PollService } from "../services/poll.service";
import { RegisteredUserService } from "../services/registered-user.service";
import {Observable} from "rxjs";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-create-poll',
  templateUrl: './create-poll.component.html',
  styleUrls: ['./create-poll.component.css']
})
export class CreatePollComponent implements OnInit {

  public poll: Poll = new Poll();
  public user: RegisteredUser = new RegisteredUser();
  public consent: boolean = false;

  constructor(private dialogRef: MatDialogRef<CreatePollComponent>, private pollService: PollService, private registeredUserService: RegisteredUserService, private router: Router) { }

  public getRegisteredUser() {
    console.log(this.registeredUserService.getUser(1));
  }

  public createPoll() {
    if (!this.isValidInput()){
      alert("You must complete the poll.");
      return;
    }
    try {
        this.pollService.createPoll(this.poll).subscribe(res => {
        alert("Poll submitted.")
        this.dialogRef.close();
    });
    } catch(error) {
      alert(error)
    }
  }

  public isValidInput(): boolean {
    return (this.poll.occupation != '' && this.poll.donationCount != 0 && this.consent)
  }

  ngOnInit(): void {
    this.registeredUserService.getUser(1).subscribe(res => {
      this.user = res;
      this.poll.registeredUser = this.user;
    });
  }

}
