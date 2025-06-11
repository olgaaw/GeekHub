import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { environment } from '../../environments/environment';
import { CreateUserRequest } from '../models/create-user-request.model';
import { ActivateAccountRequest } from '../models/activate-account-request.model';
import { LoginRequest, LoginResponse } from '../models/login.model';



@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials: LoginRequest): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${environment.apiBaseUrl}/auth/login`, credentials)
      .pipe(
        tap(response => {
          localStorage.setItem('token', response.token);
          localStorage.setItem('refreshToken', response.refreshToken);
          localStorage.setItem('userId', response.id);
          localStorage.setItem('username', response.username);
        })
      );
  }

  logout(): void {
    localStorage.removeItem('token');
    localStorage.removeItem('refreshToken');
  }

  getAccessToken(): string | null {
    return localStorage.getItem('token');
  }

  getRefreshToken(): string | null {
    return localStorage.getItem('token');
  }

  getUserId(): string | null {
    return localStorage.getItem('userId');
  }

  isLoggedIn(): boolean {
    return !!this.getAccessToken();
  }

  register(request: CreateUserRequest): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/auth/register`, request);
  }


  verifyActivation(token: string): Observable<any> {
    const body: ActivateAccountRequest = { token };
    return this.http.post<any>(`${environment.apiBaseUrl}/activate/account/`, body);
  }


}
