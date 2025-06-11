import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './components/login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { provideHttpClient } from '@angular/common/http';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { TranslatePipe } from './pipes/translate.pipe';
import { RegisterComponent } from './components/register/register.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { NavbarComponent } from './shared/navbar/navbar.component';
import { ProfileComponent } from './components/profile/profile.component';
import { UserListDialogComponent } from './shared/user-list-dialog/user-list-dialog.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TranslatePipe,
    RegisterComponent,
    ActivateAccountComponent,
    NavbarComponent,
    ProfileComponent,
    UserListDialogComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule
  ],
  providers: [  
    provideAnimationsAsync(),
    provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }