import { Component, OnInit } from '@angular/core';
import { PostService } from '../../services/post.service';
import { ExtendedPostDetails } from '../../models/post-detail.model';
import { PostResponse } from '../../models/post-response.model';
import { forkJoin } from 'rxjs';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  timelinePosts: ExtendedPostDetails[] = [];


  constructor(private postService: PostService) { }

  ngOnInit(): void {
    this.postService.getTimelinePosts().subscribe({
      next: posts => {
        const detailRequests = posts.map(post => this.postService.getPostDetails(post.id));
        forkJoin(detailRequests).subscribe({
          next: postDetails => {
            this.timelinePosts = postDetails;
          },
        });
      },
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
