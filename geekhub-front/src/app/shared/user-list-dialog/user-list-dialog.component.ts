import { Component, Input, Output, EventEmitter } from '@angular/core';
import { FavouriteUserResponse } from '../../models/favourite-user-response.model';
import { FavouriteService } from '../../services/favourite.service';
import { AuthService } from '../../services/auth.service';
import { ProfileService } from '../../services/profile.service';

@Component({
  selector: 'app-user-list-dialog',
  templateUrl: './user-list-dialog.component.html',
  styleUrls: ['./user-list-dialog.component.css']
})
export class UserListDialogComponent {
  @Input() users: FavouriteUserResponse[] = [];
  @Input() title: string = '';
  favourites: Set<string> = new Set();
  authUserId: string = '...';

  @Output() closed = new EventEmitter<void>();


  constructor(
    private favouriteService: FavouriteService,
    private authService: AuthService,
    private profileService: ProfileService
  ) { }

  ngOnInit() {
    const id = this.authService.getUserId();
    if (id) {
      this.authUserId = id;
      this.loadFavourites();
    }
  }


  close() {
    this.closed.emit();
  }

  loadFavourites() {
    this.profileService.getFollowing(this.authUserId).subscribe((data) => {
      this.favourites = new Set(data.map(user => user.id));
    });
  }

  isFavourite(userId: string): boolean {
    return this.favourites.has(userId);
  }

  toggleFavourite(userId: string) {
    if (this.isFavourite(userId)) {
      this.favouriteService.removeFavourite(userId).subscribe(() => {
        this.favourites.delete(userId);
        window.location.reload();
      });
    } else {
      this.favouriteService.addFavourite(userId).subscribe(() => {
        this.favourites.add(userId);
        window.location.reload();
      });
    }
  }

}
