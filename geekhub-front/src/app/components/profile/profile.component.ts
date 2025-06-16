import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { UserProfileDataResponse } from '../../models/user-profile-data.model';
import { ProfileService } from '../../services/profile.service';
import { PostService } from '../../services/post.service';
import { forkJoin } from 'rxjs';
import { ExtendedPostDetails } from '../../models/post-detail.model';
import { FavouriteUserResponse } from '../../models/favourite-user-response.model';
import { AuthService } from '../../services/auth.service';


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
  totalFollowing: number = 0;
  totalFollowers: number = 0;
  showDialog = false;
  dialogUsers: FavouriteUserResponse[] = [];
  dialogTitle = '';
  showModal = false;
  editProfileData: any = null;
  showDeleteModal = false;


  constructor(
    private profileService: ProfileService,
    private postService: PostService,
    private route: ActivatedRoute,
    private authService: AuthService,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.userId = this.route.snapshot.paramMap.get('id')!;

    this.profileService.getProfileById(this.userId).subscribe(response => {
      this.userProfile = response;
      this.profileImageUrl = response.profilePicture;
    });

    this.profileService.getFollowing(this.userId).subscribe(following => {
      this.totalFollowing = following.length;
    });

    this.profileService.getFollowers(this.userId).subscribe(followers => {
      this.totalFollowers = followers.length;
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

  openFollowersDialog() {
    this.profileService.getFollowers(this.userProfile.id).subscribe(users => {
      this.dialogUsers = users;
      this.dialogTitle = 'Seguidores';
      this.showDialog = true;
    });
  }

  openFollowingDialog() {
    this.profileService.getFollowing(this.userProfile.id).subscribe(users => {
      this.dialogUsers = users;
      this.dialogTitle = 'Seguidos';
      this.showDialog = true;
    });
  }

  loadProfile() {
    this.profileService.getProfileById(this.userId).subscribe(data => {
      this.userProfile = data;
      this.profileImageUrl = data.profilePicture;
    });
  }

  openEditModal() {
    this.showModal = true;
  }

  onModalClose() {
    this.showModal = false;
  }

  onProfileUpdated() {
    this.loadProfile();
  }

  canDelete(): boolean {
    const loggedInUserId = localStorage.getItem('userId') || '';
    return this.userId === loggedInUserId || this.authService.isAdmin();
  }

  deleteAccount() {
    this.profileService.deleteUser().subscribe({
      next: () => {
        localStorage.clear();

      },
    });
    this.router.navigate(['/login']), 1500;
  }

  confirmDelete(): void {
    const loggedInUserId = localStorage.getItem('userId') || '';
    this.userId = loggedInUserId;
    this.showDeleteModal = true;
  }

  cancelDelete(): void {
    this.userId = '';
    this.showDeleteModal = false;
  }

  logout(): void {
    this.authService.logout;
    this.router.navigate(['/login']), 1500;
  }

}
