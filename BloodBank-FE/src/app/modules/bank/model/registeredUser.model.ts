import { Address} from "./address.model";
import {LoyaltyCard} from "./loyaltyCard.model";

export class RegisteredUser {
  id: number = 0;
  username: string = '';
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  address: Address = new Address();
  phoneNumber: string = '';
  umcn: string = '';
  gender: string = '';
  institution: string = '';
  points: number = 0;
  isLocked: boolean = false;
  isEnabled: boolean = false;
  appUserRole: string = '';
  loyaltyCard: LoyaltyCard = new LoyaltyCard();


  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.username = obj.username;
      this.firstName = obj.firstName;
      this.lastName = obj.lastName;
      this.email = obj.email;
      this.address = obj.address;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      this.gender = obj.gender;
      this.institution = obj.institution;
      this.points = obj.points;
      this.isLocked = obj.isLocked;
      this.isEnabled = obj.isEnabled;
      this.appUserRole = obj.appUserRole;
      this.loyaltyCard = obj.loyaltyCard;
    }
  }
}
