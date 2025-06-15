export interface LoginRequest {
    username: string;
    password: string;
}

export interface LoginResponse {
    id: string;
    username: string;
    roles: string[];
    token: string;
    refreshToken: string;
}