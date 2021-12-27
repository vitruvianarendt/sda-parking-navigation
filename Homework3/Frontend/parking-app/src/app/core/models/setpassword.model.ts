import { Deserializable } from '../interfaces/deserializable.interface';
export class Setpassword implements Deserializable {
  password: string="";
  deserialize(input: any) {
    return Object.assign(this, input);
  }
}