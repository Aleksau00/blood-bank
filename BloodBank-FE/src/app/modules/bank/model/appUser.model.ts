export class AppUser{
  id: number = 0;
  username: string = '';
  password: string = '';
  firstName: string = '';
  lastName: string = '';
  phoneNumber: string = '';
  umcn: string = '';
  //gender: Gender = MALE;
  appUserRole: number = 0;
  institution: string = '';


  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.username = obj.username;
      this.password = obj.password;
      this.firstName = obj.firstName;
      this.lastName = obj.lastName;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      //this.gender = obj.gender;
      this.appUserRole = obj.appUserRole;
      this.institution = obj.institution;
    }
  }
}
