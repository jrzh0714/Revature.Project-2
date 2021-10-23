import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';
import { LoginAttempt } from '../models/loginattempt';
import { Router } from '@angular/router';



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

  constructor(private http: HttpClient, private router:Router) {}


  login(loginattempt: LoginAttempt): void {
    this.http
    .post<LoginAttempt>(`${this.baseUrl}login`,loginattempt,this.httpOptions)
    .toPromise()
    .then((res)=>
      console.log(res)
    );
      //sessionStorage.setItem('access-token',JSON.stringify(res.token))
  }


}