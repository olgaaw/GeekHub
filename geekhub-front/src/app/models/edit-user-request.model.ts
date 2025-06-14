import { InterestDto } from "./interest.model";

export interface EditUserRequest {
    username: string;
    email: string;
    name: string;
    surname: string;
    phone: string;
    address: string;
    cp: number;
    birthday: Date;
    profilePicture: string;
    bio: string;
    interests: InterestDto[];
}