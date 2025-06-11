export interface PostDetails {
    post: {
        id: string;
        userId: string;
        username: string;
        profilePicture: string,
        description: string;
        date: string;
        images: { imageUrl: string }[];
    };
    commentNum: number;
    commentLike: number;
}

export interface ExtendedPostDetails extends PostDetails {
    likedByUser?: boolean;
    likeId?: string;
}

