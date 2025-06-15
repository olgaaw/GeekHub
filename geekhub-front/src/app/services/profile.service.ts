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

  constructor(private http: HttpClient) { }


  private getAuthHeaders(): HttpHeaders | undefined {
    const token = localStorage.getItem('token');
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
  }

  getProfileById(id: string): Observable<UserProfileDataResponse> {
    return this.http.get<UserProfileDataResponse>(
      `${environment.apiBaseUrl}/user/${id}`,
      { headers: this.getAuthHeaders() }
    );
  }

  getFollowers(userId: string): Observable<FavouriteUserResponse[]> {
    return this.http.get<FavouriteUserResponse[]>(
      `${environment.apiBaseUrl}/favourite/followers/${userId}`,
      { headers: this.getAuthHeaders() }
    );
  }

  getFollowing(userId: string): Observable<FavouriteUserResponse[]> {
    return this.http.get<FavouriteUserResponse[]>(
      `${environment.apiBaseUrl}/favourite/following/${userId}`,
      { headers: this.getAuthHeaders() }
    );
  }

  editUser(formData: FormData) {
    return this.http.put<any>(`${environment.apiBaseUrl}/user/edit`, formData,
      { headers: this.getAuthHeaders() });
  }

  searchUsers(query: string): Observable<any[]> {
    return this.http.get<any[]>(
      `${environment.apiBaseUrl}/`,
      {
        params: { search: query },
        headers: this.getAuthHeaders()
      }
    );
  }




}
