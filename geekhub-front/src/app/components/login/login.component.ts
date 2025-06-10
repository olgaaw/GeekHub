import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { TranslateService } from '../../services/translate.service';

@Component({
  selector: 'app-login',
  styleUrls: ['./login.component.css'],
  templateUrl: './login.component.html',
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  showPassword = true;

  alertMessage: string | null = null;
  alertType: string = '';

  selectedLang: string;

  constructor(
    private formBuilder: FormBuilder,
    private authService: AuthService,
    private translateService: TranslateService,
    private router: Router
  ) {
    this.selectedLang = this.translateService.currentLanguageValue;

    this.translateService.currentLanguage$.subscribe(lang => {
      this.selectedLang = lang;
    });
  }

  ngOnInit() {
    this.loginForm = this.formBuilder.group({
      username: ['', Validators.required],
      password: ['', Validators.required]
    });
  }

  togglePassword() {
    this.showPassword = !this.showPassword;
  }

  showAlert(message: string, type: string) {
    this.alertMessage = message;
    this.alertType = type;
    setTimeout(() => {
      this.alertMessage = null;
    }, 3000);
  }

  onSubmit() {
    if (this.loginForm.invalid) return;

    const { username, password } = this.loginForm.value;
    this.authService.login({ username, password }).subscribe({
      next: () => {
        const userId = localStorage.getItem('userId');
        this.router.navigate(['/profile', userId]);
      },      
      error: () => {
        this.showAlert('INVALID_CREDENTIALS', 'danger');
      }
    });
  }

  changeLanguage(event: Event) {
    const select = event.target as HTMLSelectElement;
    this.translateService.setLanguage(select.value);
  }
}
