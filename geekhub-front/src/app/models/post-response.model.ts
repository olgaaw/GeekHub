export interface PostImage {
    imageUrl: string;
}

export interface PostResponse {
    userId: string;
    username: string;
    profilePicture: string,
    id: string;
    description: string;
    date: string;
    images: PostImage[];
}

