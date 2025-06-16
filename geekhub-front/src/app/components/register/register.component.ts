import { Component } from '@angular/core';
import { AuthService } from '../../services/auth.service';
import { FormGroup } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent {
  selectedLang = localStorage.getItem('language');
  showPassword = false;
  showConfirmPassword = false;

  requestForm!: FormGroup;
  alertMessage: string | null = null;
  alertType: string = '';

  currentStep = 1;

  registerData: any = {
    username: '',
    email: '',
    password: '',
    verifyPassword: '',
    name: '',
    surname: '',
    phone: '',
    address: '',
    cp: '',
    gender: '',
    birthday: ''
  };

  constructor(
    private authService: AuthService,
  ) { }


  togglePassword(type: string) {
    if (type === 'password') {
      this.showPassword = !this.showPassword;
    } else if (type === 'confirm') {
      this.showConfirmPassword = !this.showConfirmPassword;
    }
  }

  isStep1Valid(): boolean {
    const { username, email, password, confirmPassword } = this.registerData;
    const basicFieldsFilled = username.trim() !== '' && email.trim() !== '' && password.trim() !== '' && confirmPassword.trim() !== '';
    return basicFieldsFilled;
  }

  isStep2Valid(): boolean {
    const { name, surname, phone, address, cp, gender, birthday } = this.registerData;
    return name.trim() !== '' && surname.trim() !== '' && phone.trim() !== '' && address.trim() !== '' && cp.trim() !== '' && gender.trim() !== '' && birthday.trim() !== '';
  }

  goToNextStep() {
    if (this.currentStep === 1) {
      if (!this.isStep1Valid()) {
        this.showAlert('Todos los campos deben estar completos', 'danger')
        return;
      }

      const { password, confirmPassword } = this.registerData;
      if (password !== confirmPassword) {
        this.showAlert('Las contraseñas no coinciden', 'danger')
        return;
      }

    } else if (this.currentStep === 2) {
      if (!this.isStep2Valid()) {
        this.showAlert('Todos los campos deben estar completos', 'danger')
        return;
      }
    }
    this.currentStep++;
  }

  goToPreviousStep() {
    this.currentStep--;
  }

  submitRegistration() {
    const userToSend = {
      username: this.registerData.username,
      email: this.registerData.email,
      password: this.registerData.password,
      verifyPassword: this.registerData.confirmPassword,
      name: this.registerData.name,
      surname: this.registerData.surname,
      phone: this.registerData.phone,
      address: this.registerData.address,
      cp: Number(this.registerData.cp),
      gender: this.registerData.gender,
      birthday: this.registerData.birthday,
      profilePicture: this.registerData.profilePicture || null
    };

    this.authService.register(userToSend).subscribe({
      next: () => {
        this.currentStep = 4;
      },
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
