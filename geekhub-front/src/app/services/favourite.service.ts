import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class FavouriteService {

  constructor(private http: HttpClient) { }


  private getAuthHeaders(): HttpHeaders | undefined {
    const token = localStorage.getItem('token');
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
  }

  addFavourite(userId: string) {
    return this.http.post(`${environment.apiBaseUrl}/favourite/add/${userId}`, {}, {
      headers: this.getAuthHeaders()
    });
  }

  removeFavourite(userId: string) {
    return this.http.delete(`${environment.apiBaseUrl}/favourite/remove/${userId}`, {
      headers: this.getAuthHeaders()
    });
  }


}
