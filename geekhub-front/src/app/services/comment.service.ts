import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { CommentResponse } from '../models/comment-response.model';
import { environment } from '../../environments/environment';
import { PaginationDto } from '../models/pagination-dto.model';

@Injectable({ providedIn: 'root' })
export class CommentService {
  constructor(private http: HttpClient) { }

  private getAuthHeaders(): HttpHeaders | undefined {
    const token = localStorage.getItem('token');
    return token ? new HttpHeaders().set('Authorization', `Bearer ${token}`) : undefined;
  }


  getComments(postId: string): Observable<PaginationDto<CommentResponse>> {
    return this.http.get<PaginationDto<CommentResponse>>(
      `${environment.apiBaseUrl}/post/${postId}/comment/detail`,
      { headers: this.getAuthHeaders() }
    );
  }

  createComment(postId: string, commentDto: { content: string }): Observable<any> {
    return this.http.post(
      `${environment.apiBaseUrl}/post/${postId}/comment`,
      commentDto,
      { headers: this.getAuthHeaders() }
    );
  }

}
