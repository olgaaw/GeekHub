import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { PostResponse } from '../models/post-response.model';
import { PostDetails } from '../models/post-detail.model';

@Injectable({
  providedIn: 'root'
})
export class PostService {
  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders | undefined {
    const token = localStorage.getItem('token');
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
  }

  getAllPostsByUserId(userId: string): Observable<PostResponse[]> {
    return this.http.get<PostResponse[]>(`${environment.apiBaseUrl}/post/user/${userId}`);
  }


  getPostDetails(postId: string): Observable<PostDetails> {
    return this.http.get<PostDetails>(`${environment.apiBaseUrl}/post/${postId}`);
  }

  likePost(postId: string) {
    return this.http.post<any>(`${environment.apiBaseUrl}/post/${postId}/like`, null, {
      headers: this.getAuthHeaders()
    });
  }
  
  unlikePost(likeId: string) {
    return this.http.delete(`${environment.apiBaseUrl}/like/${likeId}/deletebyUser`, {
      headers: this.getAuthHeaders()
    });
  }

  

}
