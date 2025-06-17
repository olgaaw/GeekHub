import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { environment } from '../../environments/environment';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class InterestService {

  constructor(private http: HttpClient) { }


  private getAuthHeaders(): HttpHeaders | undefined {
    const token = localStorage.getItem('token');
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
  }

  getAllInterests() {
    return this.http.get<any[]>(`${environment.apiBaseUrl}/interest`,
      { headers: this.getAuthHeaders() }
    );
  }

  createInterest(formData: FormData): Observable<any> {
    return this.http.post(`${environment.apiBaseUrl}/interest`, formData, {
      headers: this.getAuthHeaders()
    });
  }

  updateInterest(id: string, formData: FormData) {
    return this.http.put(`${environment.apiBaseUrl}/interest/${id}`, formData, {
      headers: this.getAuthHeaders()
    });
  }

  deleteInterest(id: string) {
    return this.http.delete(`${environment.apiBaseUrl}/interest/${id}/delete`,
      { headers: this.getAuthHeaders() }
    );
  }



}
