export interface CreateUserRequest {
    username: string;
    email: string;
    password: string;
    verifyPassword: string;
    name: string;
    surname: string;
    phone: string;
    address: string;
    cp: number;
    gender: string;
    birthday: Date;
    profilePicture?: string | null;
  }
  