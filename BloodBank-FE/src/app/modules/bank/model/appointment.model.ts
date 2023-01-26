import {RegisteredUserDTO} from "../dto/registeredUserDTO.model";
import LocalDateTime from "ts-time/LocalDateTime";
export class Appointment{

  id: number= 0;
  registeredUserDTO: RegisteredUserDTO = new RegisteredUserDTO();
  dateTime: LocalDateTime = new LocalDateTime(); //PAZI PROMENIO NA PUBLIC
  duration: number = 0;
  description: string= "";
  status: string = "";
  /*
    CANCELED,
    FINISHED,
    DENIED
  */

  public constructor(obj?:any) {
  }

}
