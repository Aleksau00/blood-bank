import {AddressDTO} from "./addressDTO.model";

export class CenterDTO{
  name: string = '';
  address: AddressDTO = new AddressDTO();
  description: string = '';
  averageGrade: number = 0;
  startTime: string = '';
  endTime: string = '';
  id: number = 0;

  public constructor(obj?: any) {
    if (obj) {
      this.name = obj.name;
      this.address = obj.address;
      this.description = obj.description;
      this.averageGrade = obj.averageGrade;
      this.startTime = obj.startTime;
      this.endTime = obj.endTime;
      this.id = obj.id;
    }
  }
}
