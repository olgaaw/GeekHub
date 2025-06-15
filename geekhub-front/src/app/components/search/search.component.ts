import { Component } from '@angular/core';
import { ProfileService } from '../../services/profile.service';
import { FavouriteService } from '../../services/favourite.service';
import { AuthService } from '../../services/auth.service';
import { InterestService } from '../../services/interest.service';

@Component({
  selector: 'app-search',
  templateUrl: './search.component.html',
  styleUrls: ['./search.component.css']
})
export class SearchComponent {
  searchInput = '';
  gender: string = '';
  selectedInterests: Set<string> = new Set();
  interestsList: any[] = [];
  users: any[] = [];
  hasSearched = false;
  favourites: Set<string> = new Set();
  authUserId: string = '';

  constructor(
    private profileService: ProfileService,
    private favouriteService: FavouriteService,
    private authService: AuthService,
    private interestService: InterestService
  ) { }

  ngOnInit() {
    const id = this.authService.getUserId();
    if (id) {
      this.authUserId = id;
      this.loadFavourites();
    }

    this.interestService.getAllInterests().subscribe(data => {
      this.interestsList = data;
    });
  }

  onSearch() {
    const filters: string[] = [];

    if (this.searchInput.trim()) {
      filters.push(`username:${this.searchInput.trim()}`);
    }

    if (this.gender) {
      filters.push(`gender:${this.gender}`);
    }

    if (this.selectedInterests.size > 0) {
      this.selectedInterests.forEach(interest =>
        filters.push(`interests:"${interest}"`)
      );
    }

    const query = filters.join(',') + ',';

    this.profileService.searchUsers(query).subscribe(users => {
      this.users = users;
      this.hasSearched = true;
    });
  }

  toggleInterest(name: string) {
    if (this.selectedInterests.has(name)) {
      this.selectedInterests.delete(name);
    } else {
      this.selectedInterests.add(name);
    }
  }

  loadFavourites() {
    this.profileService.getFollowing(this.authUserId).subscribe(data => {
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
      });
    } else {
      this.favouriteService.addFavourite(userId).subscribe(() => {
        this.favourites.add(userId);
      });
    }
  }

}
