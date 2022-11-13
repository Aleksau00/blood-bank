import { Address} from "./address.model";
import {AddressDTO} from "../dto/addressDTO.model";

export interface Centar {
  id: number;
  name: string;
  adress: AddressDTO;
  description: string;
  averageGrade: number;
  startTime: string;
  endTime: string;
}

// const c: Centar = {
//   id: 4,
//   name: "Neki centar"
// }

export class Center {
  name: string = '';
  address: AddressDTO = new AddressDTO();
  description: string = '';
  averageGrade: number = 0;
  startTime: string = '';
  endTime: string = '';


  public constructor(obj?: any) {
    if (obj) {
      this.name = obj.name;
      this.address = obj.address;
      this.description = obj.description;
      this.averageGrade = obj.averageGrade;
      this.startTime = obj.startTime;
      this.endTime = obj.endTime;
    }
  }
}
