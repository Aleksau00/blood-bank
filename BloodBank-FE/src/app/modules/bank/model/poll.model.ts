import { RegisteredUser} from "./registeredUser.model";

export class Poll {
  registeredUser: RegisteredUser = new RegisteredUser();
  occupation: string = '';
  donationCount: number = 0;
  date: Date = new Date();

  public constructor(obj?: any) {
    if (obj) {
     this.registeredUser = obj.registeredUserDTO;
     this.occupation = obj.occupation;
     this.donationCount = obj.donationCount;
    }
  }
}
