import { Component, Input } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { CommentResponse } from '../../models/comment-response.model';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css'
})
export class CommentComponent {
  @Input() postId!: string;
  comments: CommentResponse[] = [];
  newComment: string = '';
  selectedCommentId: string | null = null;
  showDeleteModal = false;

  constructor(private commentService: CommentService, private route: ActivatedRoute, private authService: AuthService) { }

  ngOnInit(): void {
    this.postId = this.route.snapshot.paramMap.get('postId')!;
    if (this.postId) {
      this.loadComments();
    }
  }

  loadComments(): void {
    this.commentService.getComments(this.postId).subscribe({
      next: res => this.comments = res.contenido
    });
  }

  submitComment(): void {
    const trimmed = this.newComment.trim();
    if (!trimmed) return;

    this.commentService.createComment(this.postId, { content: trimmed }).subscribe({
      next: () => {
        this.newComment = '';
        this.loadComments();
      }
    });
  }

  getLoggedInUserId(): string {
    return localStorage.getItem('userId') || '';
  }


  canDelete(comment: CommentResponse): boolean {
    return this.getLoggedInUserId() === comment.userId || this.authService.isAdmin();
  }

  confirmDelete(commentId: string): void {
    this.selectedCommentId = commentId;
    this.showDeleteModal = true;
  }

  cancelDelete(): void {
    this.selectedCommentId = null;
    this.showDeleteModal = false;
  }

  deleteComment(): void {
    if (!this.selectedCommentId) return;

    const comment = this.comments.find(c => c.commentId === this.selectedCommentId);
    if (!comment) return;

    const deletion = this.canDelete(comment)
      ? this.commentService.deleteCommentByAdmin(this.selectedCommentId)
      : this.commentService.deleteCommentByUser(this.selectedCommentId);

    deletion.subscribe({
      next: () => {
        this.loadComments();
        this.cancelDelete();
      },
      error: () => {
        alert('Error al eliminar el comentario');
        this.cancelDelete();
      }
    });
  }

}


