import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-activate-account',
  templateUrl: './activate-account.component.html',
  styleUrl: './activate-account.component.css'
})
export class ActivateAccountComponent {
  code: string[] = ['', '', '', ''];
  alertMessage: string | null = null;
  alertType: string = '';

  constructor(private authService: AuthService, private router: Router) {}

  focusNext(index: number, event: any) {
    const input = event.target;
    if (input.value && index < 3) {
      const nextInput = input.parentElement.children[index + 1];
      if (nextInput) {
        nextInput.focus();
      }
    }
  }

  submitCode() {
    const token = this.code.join('');
    if (token.length < 4) {
      this.showAlert('INVALID_CODE', 'danger');
      return;
    }

    this.authService.verifyActivation(token).subscribe({
      next: () => {
        this.showAlert('ACCOUNT_VERIFIED', 'success');
        setTimeout(() => this.router.navigate(['/login']), 1500);
      },
      error: () => this.showAlert('INVALID_OR_EXPIRED', 'danger')
    });
  }

  showAlert(message: string, type: string) {
    this.alertMessage = message;
    this.alertType = type;
    setTimeout(() => {
      this.alertMessage = null;
    }, 3000);
  }
}

