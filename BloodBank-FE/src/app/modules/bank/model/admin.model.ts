export class Admin{
  id: number = 0;
  username: string = '';
  password: string = '';
  firstName: string = '';
  lastName: string = '';
  phoneNumber: string = '';
  umcn: string = '';
  appUserRole: number = 0;
  institution: string = '';
  firstLogin: boolean = false;

  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.username = obj.username;
      this.password = obj.password;
      this.firstName = obj.firstName;
      this.lastName = obj.lastName;
      this.phoneNumber = obj.phoneNumber;
      this.umcn = obj.umcn;
      this.appUserRole = obj.appUserRole;
      this.institution = obj.institution;
      this.firstLogin = obj.firstLogin;
    }
  }
}
