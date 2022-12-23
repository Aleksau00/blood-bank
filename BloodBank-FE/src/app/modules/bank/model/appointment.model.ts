import {RegisteredUserDTO} from "../dto/registeredUserDTO.model";
import LocalDateTime from "ts-time/LocalDateTime";
import { Blood } from "./blood.model";
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

export class AppointmentUpdate{

  id: number= 0;
  registeredUserDTO: RegisteredUserDTO = new RegisteredUserDTO();
  dateTime: LocalDateTime = new LocalDateTime(); //PAZI PROMENIO NA PUBLIC
  duration: number = 0;
  description: string= "";
  status: number = 0;
  appointmentEquipment: number = 0;
  blood: Blood = new Blood();

  public constructor(obj?:any) {
  }

}
