import { InterestDto } from "./interest.model";

export interface UserProfileDataResponse {
    id: string,
    username: string;
    name: string;
    gender: string;
    profilePicture: string;
    bio: string;
    cp: string,
    interests: InterestDto[];
}

