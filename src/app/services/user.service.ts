import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { User } from '../models/user';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  baseUrl: string = `http://localhost:8001/users`;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    }),
  };

  constructor(private http: HttpClient) {}


  getUserByUsername(username: string): Observable<User> {
    return this.http.get<User>(`${this.baseUrl}/userByUsername/${username}`, this.httpOptions);
  }

  getUsernameById(id: number): Observable<String>  {
    return this.http.get(`${this.baseUrl}/usernameById/${id}`,  {responseType: 'text'});
  }

  register(newuser: User): Observable<User> {
    console.log(newuser);
    return this.http.post<User>(`http://localhost:8001/register`,newuser,this.httpOptions);
  }
}
