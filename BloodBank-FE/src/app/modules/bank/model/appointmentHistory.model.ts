import LocalDateTime from "ts-time/LocalDateTime";
import LocalTime from "ts-time/LocalTime";
import { RegisteredUserDTO } from "../dto/registeredUserDTO.model";
import { Blood } from "./blood.model";
import { Center } from "./center.model";

export class AppointmentHistory{

    id: number= 0;
    date : Date = new Date();
    time: LocalTime = new LocalTime();
 //   center: Center = new Center();
    duration: number = 0;
    description: string= "";
  



    public constructor(obj?:any) {
    }
  
  }