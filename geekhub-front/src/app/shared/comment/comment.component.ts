import { Component, Input } from '@angular/core';
import { CommentService } from '../../services/comment.service';
import { CommentResponse } from '../../models/comment-response.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-comment',
  templateUrl: './comment.component.html',
  styleUrl: './comment.component.css'
})
export class CommentComponent {
  @Input() postId!: string;
  comments: CommentResponse[] = [];
  newComment: string = '';

  constructor(private commentService: CommentService, private route: ActivatedRoute) { }

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
}


