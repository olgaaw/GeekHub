import { InterestDto } from "./interest.model";

export interface UserProfileDataResponse {
    username: string;
    name: string;
    gender: string;
    profilePicture: string;
    bio: string;
    cp: string,
    interests: InterestDto[];
}

