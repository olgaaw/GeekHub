import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserProfileDataResponse } from '../../models/user-profile-data.model';
import { ProfileService } from '../../services/profile.service';
import { PostService } from '../../services/post.service';
import { forkJoin } from 'rxjs';
import { ExtendedPostDetails } from '../../models/post-detail.model';

@Component({
  selector: 'app-profile',
  templateUrl: './profile.component.html',
  styleUrls: ['./profile.component.css']
})
export class ProfileComponent implements OnInit {
  userProfile!: UserProfileDataResponse;
  profileImageUrl: string = '';
  userId!: string;
  totalPosts: number = 0;
  activeTab: 'details' | 'posts' = 'details';
  userPosts: ExtendedPostDetails[] = [];

  constructor(
    private profileService: ProfileService,
    private postService: PostService,
    private route: ActivatedRoute
  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id')!;
  
    this.profileService.getProfileById(this.userId).subscribe(response => {
      this.userProfile = response;
      this.profileImageUrl = response.profilePicture;
    });
  
    this.postService.getAllPostsByUserId(this.userId).subscribe({
      next: posts => {
        this.totalPosts = posts.length;
    
        const detailRequests = posts.map(post =>
          this.postService.getPostDetails(post.id)
        );
    
        forkJoin(detailRequests).subscribe({
          next: postDetails => {
            this.userPosts = postDetails;
          },
        });
      },
    });
    
  }

  toggleLike(post: ExtendedPostDetails) {
    if (post.likedByUser && post.likeId) {
      this.postService.unlikePost(post.likeId).subscribe({
        next: () => {
          post.likedByUser = false;
          post.commentLike--;
        },
      });
    } else {
      this.postService.likePost(post.post.id).subscribe({
        next: (res) => {
          post.likedByUser = true;
          post.likeId = res.id; 
          post.commentLike++;
        },
      });
    }
  }

}
