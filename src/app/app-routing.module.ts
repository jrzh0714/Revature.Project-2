import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { LoginComponent } from './views/login/login.component';
import { RecipeViewComponent } from './views/recipe/recipe-view.component';
import { RegisterComponent } from './views/register/register.component';
import { HomeComponent } from './views/home/home.component';
import { UserViewComponent } from './views/user/user-view.component';


const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  {
    path: 'recipe/:id',
    component: RecipeViewComponent,
  },
  {
    path: 'user/:username',
    component: UserViewComponent,
  },
  {
    path: 'register',
    component: RegisterComponent,
  },

];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
