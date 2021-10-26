import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { LoginAttempt } from '../models/loginattempt';
import { Router } from '@angular/router';
import { UserService } from './user.service';
import { Subject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class AuthService {


  baseUrl: string = `http://localhost:8001/`;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    }),
  };

  constructor(private http: HttpClient,private userService: UserService, private router:Router) {}


  login(loginattempt: LoginAttempt): void {
    this.http
    .post<any>(`${this.baseUrl}login`,loginattempt,this.httpOptions)
    .toPromise()
    .then((response)=>
      {
        var token = response.token;
      sessionStorage.setItem('access-token',token)
      if(token != null){
        sessionStorage.setItem('username',loginattempt.username)
        this.userService.getUserByUsername(loginattempt.username).toPromise().then((res)=>{
          console.log(res);

        });

      }
      }
    );
    
  }


}