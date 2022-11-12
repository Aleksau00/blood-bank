import { Address} from "./address.model";
import {AddressDTO} from "../dto/addressDTO.model";

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
