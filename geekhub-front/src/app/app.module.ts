import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbDropdownModule, NgbModule } from '@ng-bootstrap/ng-bootstrap';
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
import { HomeComponent } from './components/home/home.component';
import { PostComponent } from './shared/post/post.component';
import { PostDetailComponent } from './components/post-detail/post-detail.component';
import { CommentComponent } from './shared/comment/comment.component';
import { CreatePostComponent } from './shared/create-post/create-post.component';
import { EditProfileComponent } from './components/edit-profile/edit-profile.component';
import { MatDialogModule } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatSelectModule } from '@angular/material/select';
import { SearchComponent } from './components/search/search.component';
import { InterestComponent } from './components/interest/interest.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    TranslatePipe,
    RegisterComponent,
    ActivateAccountComponent,
    NavbarComponent,
    ProfileComponent,
    UserListDialogComponent,
    HomeComponent,
    PostComponent,
    PostDetailComponent,
    CommentComponent,
    CreatePostComponent,
    EditProfileComponent,
    SearchComponent,
    InterestComponent

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    MatDialogModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    NgbDropdownModule

  ],
  providers: [
    provideAnimationsAsync(),
    provideHttpClient()],
  bootstrap: [AppComponent]
})
export class AppModule { }