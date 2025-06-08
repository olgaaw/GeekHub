import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { UserProfileDataResponse } from '../models/user-profile-data.model';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ProfileService {

  constructor(private http: HttpClient) {}

  getProfileById(id: string): Observable<UserProfileDataResponse> {
    return this.http.get<UserProfileDataResponse>(`${environment.apiBaseUrl}/user/${id}`);
  }
}
