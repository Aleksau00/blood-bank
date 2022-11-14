export class LoyaltyCard {
  id: number = 0;
  name: string = '';
  points: number = 0;

  public constructor(obj?: any) {
    if (obj) {
      this.id = obj.id;
      this.name = obj.name;
      this.points = obj.points;
    }
  }
}
