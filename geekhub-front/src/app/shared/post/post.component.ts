import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ExtendedPostDetails } from '../../models/post-detail.model';

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

}
