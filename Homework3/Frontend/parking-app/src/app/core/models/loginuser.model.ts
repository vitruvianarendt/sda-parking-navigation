import { Deserializable } from '../interfaces/deserializable.interface';
export class Loginuser implements Deserializable{
  email: string =""; 
  password: string ="";
  deserialize(input: any) {
    return Object.assign(this, input);
  }
}