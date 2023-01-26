import {RegisteredUserDTO} from "../dto/registeredUserDTO.model";
import LocalDateTime from "ts-time/LocalDateTime";
export class AppointmentUpdateDTO{
  date: Date = new Date();
  duration: number = 0;
  time: string = "";



  public constructor(obj?:any) {
    if(obj) {
      this.date = obj.date;
      this.duration = obj.duration;
      this.time = obj.time;

    }

  }

}
