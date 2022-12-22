import {RegisteredUserDTO} from "../dto/registeredUserDTO.model";
import LocalDateTime from "ts-time/LocalDateTime";
export class AppointmentDTO{
  subject: string = ""
  dateTime: Date = new Date(); //PAZI PROMENIO NA PUBLIC
  endTime: Date = new Date();
  public constructor(obj?:any) {
    this.dateTime = obj.dateTime;
    this.endTime = obj.dateTime.plusMinutes(obj.duration);
    this.subject = obj.firstName;
  }

}
