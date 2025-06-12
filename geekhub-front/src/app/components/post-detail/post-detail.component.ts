import { Component } from '@angular/core';
import { PostService } from '../../services/post.service';
import { forkJoin } from 'rxjs';
import { ExtendedPostDetails } from '../../models/post-detail.model';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-post-detail',
  templateUrl: './post-detail.component.html',
  styleUrl: './post-detail.component.css'
})
export class PostDetailComponent {
  postDetail?: ExtendedPostDetails;
  postId!: string;


  constructor(private postService: PostService, private route: ActivatedRoute) { }


  ngOnInit(): void {
    this.postId = this.route.snapshot.paramMap.get('postId')!;
    console.log('post id', this.postId)
    this.postService.getPostDetails(this.postId).subscribe({
      next: detail => {
        this.postDetail = detail;
      }
    });
  }

  onToggleLike(post: ExtendedPostDetails) {
    if (post.likedByUser && post.likeId) {
      this.postService.unlikePost(post.likeId).subscribe(() => {
        post.likedByUser = false;
        post.commentLike--;
      });
    } else {
      this.postService.likePost(post.post.id).subscribe(res => {
        post.likedByUser = true;
        post.likeId = res.id;
        post.commentLike++;
      });
    }
  }

}
