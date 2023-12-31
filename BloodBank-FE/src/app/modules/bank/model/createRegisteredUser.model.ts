import { Address} from "./address.model";
import {AddressDTO} from "../dto/addressDTO.model";

export class CreateRegisteredUser {
  username: string = '';
  firstName: string = '';
  password: string = '';
  lastName: string = '';
  address: AddressDTO = new AddressDTO();
  phoneNumber: string = '';
  umcn: string = '';
  gender: string = '';
  institution: string = '';
  isEnabled: boolean = false;
  isLocked: boolean = false;
  appUserRole: number = 0;


  public constructor(obj?: any) {
    if (obj) {
      this.username = obj.username;
      this.firstName = obj.firstName;
      this.password = obj.password;
      this.lastName = obj.lastName;
      this.address = obj.address;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      this.gender = obj.gender;
      this.institution = obj.institution;
      this.isEnabled = obj.isEnabled;
      this.isLocked = obj.isLocked;
      this.appUserRole = obj.appUserRole;
    }
  }
}
