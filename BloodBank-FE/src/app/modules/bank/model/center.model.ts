import { Address} from "./address.model";

export class Center {
  id: number = 0;
  name: string = '';
  address: Address = new Address();
  description: string = '';
  averageGrade: number = 0;
  startTime: string = '';
  endTime: string = '';


  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.name = obj.name;
      this.address = obj.address;
      this.description = obj.description;
      this.averageGrade = obj.averageGrade;
      this.startTime = obj.startTime;
      this.endTime = obj.endTime;
    }
  }
}
