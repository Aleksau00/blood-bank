export class UserToken {
  sub: string = '';
  id: number = 0;
  role: number = 0;

  constructor(sub: string, id: number, role: number ) {
    this.sub = sub;
    this.id = id;
    this.role = role;
  }
}
