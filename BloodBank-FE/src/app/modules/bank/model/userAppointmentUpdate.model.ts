import LocalTime from "ts-time/LocalTime";
import { RegisteredUserDTO } from "../dto/registeredUserDTO.model";
import { Blood } from "./blood.model";
import { Center } from "./center.model";

export class UserAppointmentUpdate{

  id: number= 0;
  registeredUserDTO: RegisteredUserDTO = new RegisteredUserDTO();
  date : Date = new Date();
  time: LocalTime = new LocalTime();
  center: Center = new Center();
  duration: number = 0;
  description: string= "";
  status: number = 0;
  appointmentEquipment: number = 0;
  blood: Blood = new Blood();

  public constructor(obj?:any) {
  }

}
