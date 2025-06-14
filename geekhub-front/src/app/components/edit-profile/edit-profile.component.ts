import { Component, EventEmitter, Inject, Input, OnInit, Output } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { InterestService } from '../../services/interest.service';

@Component({
  selector: 'app-edit-profile',
  templateUrl: './edit-profile.component.html',
  styleUrls: ['./edit-profile.component.css']
})
export class EditProfileComponent implements OnInit {
  @Input() show = false;
  @Input() profileData: any;
  @Output() close = new EventEmitter<void>();
  @Output() updated = new EventEmitter<void>();

  editProfile: any = {
    name: '',
    username: '',
    bio: '',
    cp: '',
    interests: []
  };

  allInterests: any[] = [];
  selectedFile: File | null = null;

  constructor(
    private interestService: InterestService,
    private profileService: ProfileService
  ) { }

  ngOnInit(): void {
    this.loadInterests();
    if (this.profileData) {
      this.editProfile = {
        ...this.profileData,
        interests: this.profileData.interests?.map((i: any) => i.name) || []
      };
    }
  }

  loadInterests() {
    this.interestService.getAllInterests().subscribe(data => {
      this.allInterests = data;
    });
  }

  onFileSelected(event: any) {
    this.selectedFile = event.target.files[0];
  }

  submitEditProfile() {
    const formData = new FormData();

    const dto = {
      ...this.editProfile,
      interests: this.editProfile.interests?.map((name: string) => ({ name })) || []
    };

    formData.append('editUserCmd', new Blob([JSON.stringify(dto)], {
      type: 'application/json'
    }));

    if (this.selectedFile) {
      formData.append('file', this.selectedFile);
    }

    this.profileService.editUser(formData).subscribe(() => {
      this.updated.emit();
      this.close.emit();
    });
  }

  closeModal() {
    this.close.emit();
  }
}
