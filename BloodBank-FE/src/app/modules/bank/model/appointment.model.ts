import {RegisteredUserDTO} from "../dto/registeredUserDTO.model";
import LocalDateTime from "ts-time/LocalDateTime";
import LocalTime from "ts-time/LocalTime";
import {Time} from "@angular/common";
import {Center} from "./center.model";
export class Appointment{
  id: number= 0;
  registeredUserDTO: RegisteredUserDTO = new RegisteredUserDTO();
  date : Date = new Date();
  time: LocalTime = new LocalTime();
  center: Center = new Center();
  duration: number = 0;

  public constructor(obj?:any) {
  }

}
