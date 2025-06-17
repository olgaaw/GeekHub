import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrl: './navbar.component.css'
})
export class NavbarComponent {
  userId: string | null = null;

  constructor(private authService: AuthService) { }

  ngOnInit() {
    this.userId = this.authService.getUserId();
  }

  isAdmin(): boolean {
    if (this.authService.isAdmin()) {
      return true
    } else {
      return false
    }
  }

}
