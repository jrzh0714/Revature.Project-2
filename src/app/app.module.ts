import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import {MatInputModule} from '@angular/material/input';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderComponent } from './components/header/header.component';
import { FeaturedComponent } from './components/featured/featured.component';
import { SearchComponent } from './components/search/search.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UserinfoComponent } from './components/userinfo/userinfo.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RecipeViewComponent } from './views/recipe/recipe-view.component';
import { LoginComponent } from './views/login/login.component';
import { RegisterComponent } from './views/register/register.component';
import { RecipeComponent } from './components/recipe/recipe.component';
import { HomeComponent } from './views/home/home.component';
import { MatIconModule } from '@angular/material/icon';
import { UserViewComponent } from './views/user/user-view.component';
import { UserComponent } from './components/user/user.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import {IvyCarouselModule} from 'angular-responsive-carousel';
import { CreateComponent } from './views/create/create.component';
import { RecipeformComponent } from './components/recipeform/recipeform.component';
import { SearchViewComponent } from './views/search/search.view.component';
import { Logout } from './views/logout.view/logout.view.component';
import { LoadingScreenComponent } from './components/loading-screen/loading-screen.component';
import { LoadingScreenInterceptor } from './loading.interceptor';


@NgModule({
  declarations: [
    AppComponent,
    HeaderComponent,
    FeaturedComponent,
    SearchComponent,
    UserinfoComponent,
    RecipeViewComponent,
    LoginComponent,
    RegisterComponent,
    RecipeComponent,
    HomeComponent,
    UserViewComponent,
    UserComponent,
    CreateComponent,
    RecipeformComponent,
    SearchViewComponent,
    Logout,
    LoadingScreenComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    MatInputModule,
    HttpClientModule,
    MatIconModule,
    FormsModule,
    ReactiveFormsModule,
    IvyCarouselModule,
    

  ],
  providers: [{
    provide: HTTP_INTERCEPTORS,
    useClass: LoadingScreenInterceptor,
    multi: true
  }],
  bootstrap: [AppComponent]
})
export class AppModule { }
