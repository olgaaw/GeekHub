import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { ProfileService } from '../../services/profile.service';
import { UserProfileDataResponse } from '../../models/user-profile-data.model';
import { CreatePostRequest } from '../../models/create-post-request.model';
import { PostService } from '../../services/post.service';

@Component({
  selector: 'app-create-post',
  templateUrl: './create-post.component.html',
  styleUrl: './create-post.component.css'
})
export class CreatePostComponent {
  profilePicture: string = '';
  newPostText: string = '';
  selectedFiles: File[] = [];

  constructor(
    private authService: AuthService,
    private profileService: ProfileService,
    private postService: PostService
  ) { }

  ngOnInit(): void {
    const userId = this.authService.getUserId();
    if (userId) {
      this.profileService.getProfileById(userId).subscribe({
        next: (profile: UserProfileDataResponse) => {
          this.profilePicture = profile.profilePicture;
        },
      });
    }
  }

  onFileSelected(event: any): void {
    const files: FileList = event.target.files;
    this.selectedFiles = Array.from(files);
  }

  createPost(): void {
    if (!this.newPostText.trim()) return;

    const postData: CreatePostRequest = {
      description: this.newPostText,
      images: []
    };

    const formData = new FormData();
    formData.append('post', new Blob([JSON.stringify(postData)], { type: 'application/json' }));

    if (this.selectedFiles.length > 0) {
      this.selectedFiles.forEach(file => formData.append('files', file));
    }

    this.postService.createPost(formData).subscribe({
      next: () => {
        this.newPostText = '';
        this.selectedFiles = [];
      }
    });
  }
}
