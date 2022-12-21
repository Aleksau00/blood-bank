import { Component, OnInit } from '@angular/core';
import {View, EventSettingsModel} from "@syncfusion/ej2-angular-schedule";
import {DataManager, WebApiAdaptor} from "@syncfusion/ej2-data";

@Component({
  selector: 'app-working-schedule',
  template: '<ejs-schedule> </ejs-schedule>',
  //templateUrl: './working-schedule.component.html',
  styleUrls: ['./working-schedule.component.css']
})
export class WorkingScheduleComponent implements OnInit {

  private eventData: DataManager = new DataManager({
    url: 'http://localhost:8082/api/appointments/all',
    adaptor: new WebApiAdaptor(),
    crossDomain: true
  });

  public eventObject: EventSettingsModel ={
    dataSource : this.eventData

  }
  constructor() { }

  ngOnInit(): void {
  }

}
