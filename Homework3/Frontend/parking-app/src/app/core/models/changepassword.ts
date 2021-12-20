import { Deserializable } from '../interfaces/deserializable.interface';
export class Changepassword implements Deserializable {
    old_password: string ="";
    new_password: string ="";
    deserialize(input: any) {
        return Object.assign(this, input);
    }
}