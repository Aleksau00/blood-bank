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

  public center: Center = new Center();
  public consent: boolean = false;

  ngOnInit(): void {
  }

  constructor(private centerService: CenterService, private router: Router) {
  }

  public createCenter() {
    if (!this.isValidInput()) {
      alert("Center cannot be empty.");
      return;
    }
    try {
      this.centerService.saveCenter(this.center).subscribe(res => {
        alert("Poll submitted.")
      });
    } catch (error) {
      alert(error)
    }
  }
  public isValidInput(): boolean {
      return (this.center.name != '')
  }


}
