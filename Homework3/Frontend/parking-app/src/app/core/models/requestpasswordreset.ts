
import { Deserializable } from '../interfaces/deserializable.interface';
export class Requestpasswordreset implements Deserializable {
    email: string="";
    deserialize(input: any) {
        return Object.assign(this, input);
    }
}