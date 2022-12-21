
export class RegisteredUserDTO{
  name: string = '';
  lastName: string = '';

  public constructor(obj?: any){
    if(obj){
      this.name = obj.firstName;
      this.lastName = obj.lastName;
    }
  }

}
