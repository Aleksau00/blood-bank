import { RegisteredUser} from "./registeredUser.model";

export class Poll {
  registeredUser: RegisteredUser = new RegisteredUser();
  occupation: string = '';
  donationCount: number = 0;
  date: Date = new Date();
  question1: boolean = false;
  question2: boolean = false;
  question3: boolean = false;
  question4: boolean = false;
  question5: boolean = false;
  question6: boolean = false;
  question7: boolean = false;

  public constructor(obj?: any) {
    if (obj) {
     this.registeredUser = obj.registeredUserDTO;
     this.occupation = obj.occupation;
     this.donationCount = obj.donationCount;
     this.question1 = obj.question1;
     this.question2 = obj.question2;
     this.question3 = obj.question3;
     this.question4 = obj.question4;
     this.question5 = obj.question5;
     this.question6 = obj.question6;
     this.question7 = obj.question7;
    }
  }
}
