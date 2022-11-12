import { Component, OnInit } from '@angular/core';
import {Center} from "../model/center.model";
import {Router} from "@angular/router";
import {CenterService} from "../services/center.service";

@Component({
  selector: 'app-create-center',
  templateUrl: './create-center.component.html',
  styleUrls: ['./create-center.component.css']
})
export class CreateCenterComponent implements OnInit {

  ngOnInit(): void {
  }

  public center: Center = new Center();

  constructor(private centerService: CenterService, private router: Router) {
  }

  public createCenter() {
    if (!this.isValidInput()) {
      alert("Center cannot be empty.");
      return;
    }
    try {
      /*this.centerService.(this.feedback).subscribe(res => {
        alert("Feedback sent for review.")
        this.router.navigate(['/home']);
      });*/
    } catch (error) {
      alert(error)
    }

  }

  private isValidInput(): boolean {
    return this.center.name != '';
  }
}
