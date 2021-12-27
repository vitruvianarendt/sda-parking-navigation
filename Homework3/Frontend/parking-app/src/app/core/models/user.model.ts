import { Deserializable } from '../interfaces/deserializable.interface';
export class User implements Deserializable {
  username: string="";
  email: string="";
  password: string="";
  deserialize(input: any) {
    return Object.assign(this, input);
  }
}