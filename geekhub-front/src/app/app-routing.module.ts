import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { RegisterComponent } from './components/register/register.component';
import { ActivateAccountComponent } from './components/activate-account/activate-account.component';
import { ProfileComponent } from './components/profile/profile.component';
import { HomeComponent } from './components/home/home.component';
import { PostDetailComponent } from './components/post-detail/post-detail.component';
import { SearchComponent } from './components/search/search.component';
import { InterestComponent } from './components/interest/interest.component';

const routes: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'register', component: RegisterComponent },
  { path: 'activation', component: ActivateAccountComponent },
  { path: 'profile/:id', component: ProfileComponent },
  { path: 'home', component: HomeComponent },
  { path: 'post/:postId', component: PostDetailComponent },
  { path: 'search', component: SearchComponent },
  { path: 'interest', component: InterestComponent },
  { path: '**', redirectTo: 'login' }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
