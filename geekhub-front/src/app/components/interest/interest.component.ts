import { Component } from '@angular/core';
import { InterestService } from '../../services/interest.service';
import { InterestDto } from '../../models/interest.model';

@Component({
  selector: 'app-interest',
  templateUrl: './interest.component.html',
  styleUrl: './interest.component.css'
})
export class InterestComponent {
  interests: InterestDto[] = [];
  showModal = false;
  showDeleteModal = false;
  newInterestName: string = '';
  selectedFile: File | null = null;
  editingInterest: InterestDto | null = null;
  interestToDelete: InterestDto | null = null;

  constructor(private interestService: InterestService) { }

  ngOnInit(): void {
    this.loadInterests();
  }

  loadInterests(): void {
    this.interestService.getAllInterests().subscribe({
      next: data => this.interests = data
    });
  }

  openModal(interest?: InterestDto): void {
    this.showModal = true;
    if (interest) {
      this.editingInterest = interest;
      this.newInterestName = interest.name;
      this.selectedFile = null;
    } else {
      this.editingInterest = null;
      this.newInterestName = '';
      this.selectedFile = null;
    }
  }

  closeModal(): void {
    this.showModal = false;
  }

  onFileSelected(event: any): void {
    if (event.target.files.length > 0) {
      this.selectedFile = event.target.files[0];
    }
  }

  createOrUpdateInterest(): void {
    if (!this.newInterestName.trim()) return;

    const formData = new FormData();
    const interestDto = { name: this.newInterestName };

    formData.append('interestDto', new Blob([JSON.stringify(interestDto)], { type: 'application/json' }));

    if (this.selectedFile) {
      formData.append('file', this.selectedFile);
    }

    if (this.editingInterest) {
      this.interestService.updateInterest(this.editingInterest.id, formData).subscribe({
        next: () => {
          this.loadInterests();
          this.closeModal();
        }
      });
    } else {
      this.interestService.createInterest(formData).subscribe({
        next: () => {
          this.loadInterests();
          this.closeModal();
        }
      });
    }
  }

  confirmDelete(interest: any) {
    this.interestToDelete = interest;
    this.showDeleteModal = true;
  }

  cancelDelete() {
    this.interestToDelete = null;
    this.showDeleteModal = false;
  }


  deleteInterest() {
    if (!this.interestToDelete?.id) return;

    this.interestService.deleteInterest(this.interestToDelete.id).subscribe({
      next: () => {
        this.loadInterests();
        this.cancelDelete();
      }
    });
  }


}
