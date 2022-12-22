
export class RegisteredUserDTO{
  id: number = 0;
  name: string = '';
  lastName: string = '';

  public constructor(obj?: any){
    if(obj){
      this.id = obj.id;
      this.name = obj.firstName;
      this.lastName = obj.lastName;
    }
  }

}
