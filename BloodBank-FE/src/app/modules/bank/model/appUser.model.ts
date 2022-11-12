export class AppUser{
  id: number = 0;
  username: string = '';
  password: string = '';
  email: string = '';
  firstName: string = '';
  lastName: string = '';
  phoneNumber: string = '';
  umcn: string = '';
  //gender: Gender = MALE;
  institution: string = '';


  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.username = obj.username;
      this.password = obj.password;
      this.email = obj.email;
      this.firstName = obj.firstName;
      this.lastName = obj.lastName;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      //this.gender = obj.gender;
      this.institution = obj.institution;
    }
  }
}
