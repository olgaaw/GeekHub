import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserProfileDataResponse } from '../models/user-profile-data.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { FavouriteUserResponse } from '../models/favourite-user-response.model';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {}

  getProfileById(id: string): Observable<UserProfileDataResponse> {
    return this.http.get<UserProfileDataResponse>(`${environment.apiBaseUrl}/user/${id}`);
  }

  getFollowers(userId: string): Observable<FavouriteUserResponse[]> {
    return this.http.get<FavouriteUserResponse[]>(`${environment.apiBaseUrl}/favourite/followers/${userId}`);
  }

  getFollowing(userId: string): Observable<FavouriteUserResponse[]> {
    return this.http.get<FavouriteUserResponse[]>(`${environment.apiBaseUrl}/favourite/following/${userId}`);
  }
  
}
