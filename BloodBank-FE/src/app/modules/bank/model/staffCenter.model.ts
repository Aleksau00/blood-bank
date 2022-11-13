import {AddressDTO} from "../dto/addressDTO.model";
import { CenterDTO } from "../dto/centerDTO.model";

export class Staff{
  username: string = '';
  password: string = '';
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  address: AddressDTO = new AddressDTO();
  phoneNumber: string = '';
  umcn: string = '';
  gender: string = '';
  institution: string = '';
  center: CenterDTO = new CenterDTO();

  public constructor(obj?: any) {
    if(obj){
      this.username = obj.username;
      this.password = obj.password;
      this.firstName = obj.firstName;
      this.lastName = obj.lastName;
      this.email = obj.email;
      this.address = obj.address;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      this.gender = obj.gender;
      this.institution = obj.institution;
      this.center = obj.center;

    }

  }

}
