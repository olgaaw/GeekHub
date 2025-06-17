import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ExtendedPostDetails } from '../../models/post-detail.model';
import { PostService } from '../../services/post.service';
import { HttpErrorResponse } from '@angular/common/http';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrl: './post.component.css'
})
export class PostComponent {
  @Input() posts: ExtendedPostDetails[] = [];
  @Input() profileImageUrl: string = '';
  @Input() username: string = '';
  @Input() postId: string = '';
  @Input() userId: string = '';
  @Output() likeToggle = new EventEmitter<ExtendedPostDetails>();
  showDeleteModal = false;
  postToDelete?: ExtendedPostDetails;

  constructor(private postService: PostService, private authService: AuthService) { }

  onToggleLike(post: ExtendedPostDetails) {
    this.likeToggle.emit(post);
  }

  getProfileImage(post: ExtendedPostDetails): string {
    return post.post?.profilePicture || this.profileImageUrl || 'https://upload.wikimedia.org/wikipedia/commons/8/89/Portrait_Placeholder.png';
  }

  getUsername(post: ExtendedPostDetails): string {
    return post.post?.username || this.username;
  }

  getPostId(post: ExtendedPostDetails): string {
    return post.post?.id || this.postId;
  }

  getUserId(post: ExtendedPostDetails): string {
    return post.post?.userId || this.userId;
  }

  canDelete(post: ExtendedPostDetails): boolean {
    const loggedInUserId = localStorage.getItem('userId') || '';
    return this.getUserId(post) === loggedInUserId || this.authService.isAdmin();
  }

  openDeleteModal(post: ExtendedPostDetails) {
    this.postToDelete = post;
    this.showDeleteModal = true;
  }

  closeDeleteModal() {
    this.showDeleteModal = false;
    this.postToDelete = undefined;
  }

  deletePost() {
    if (!this.postToDelete) return;

    const postId = this.getPostId(this.postToDelete);

    if (this.authService.isAdmin()) {
      const postId = this.getPostId(this.postToDelete);
      this.postService.deletePostByAdmin(postId).subscribe({

        next: () => {
          this.posts = this.posts.filter(p => this.getPostId(p) !== postId);
          this.closeDeleteModal();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error?.message || 'Error eliminando la publicación');
          this.closeDeleteModal();

        }
      });
    } else {
      this.postService.deletePostByUser(postId).subscribe({
        next: () => {
          this.posts = this.posts.filter(p => this.getPostId(p) !== postId);
          this.closeDeleteModal();
        },
        error: (error: HttpErrorResponse) => {
          alert(error.error?.message || 'Error eliminando la publicación');
          this.closeDeleteModal();

        }
      });
    }


  }

}
