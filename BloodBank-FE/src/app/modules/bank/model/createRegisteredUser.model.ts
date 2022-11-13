import { Address} from "./address.model";
import {AddressDTO} from "../dto/addressDTO.model";

export class CreateRegisteredUser {
  username: string = '';
  firstName: string = '';
  password: string = '';
  lastName: string = '';
  email: string = '';
  address: AddressDTO = new AddressDTO();
  phoneNumber: string = '';
  umcn: string = '';
  gender: string = '';
  institution: string = '';


  public constructor(obj?: any) {
    if (obj) {
      this.username = obj.username;
      this.firstName = obj.firstName;
      this.password = obj.password;
      this.lastName = obj.lastName;
      this.email = obj.email;
      this.address = obj.address;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      this.gender = obj.gender;
      this.institution = obj.institution;
    }
  }
}
