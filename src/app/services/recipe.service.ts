import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { Recipe } from '../models/recipe';

@Injectable({
  providedIn: 'root'
})
export class RecipeService {

  baseUrl: string = `http://localhost:8001/recipes`;

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type': 'application/json',
      'Access-Control-Allow-Origin': '*',
    }),
  };

  constructor(private http: HttpClient) {}


  getRecipe(id: number): Observable<Recipe> {
    return this.http.get<Recipe>(`${this.baseUrl}/${id}`, this.httpOptions);
  }
  getTrending(): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.baseUrl}/trending`, this.httpOptions);
  }
  addRecipe(newrecipe:any): Observable<Recipe[]> {
    return this.http.post<Recipe[]>(`${this.baseUrl}/submitRecipe`, newrecipe,this.httpOptions);
  }

  getRecipesByName(searchterm:string): Observable<Recipe[]> {
    return this.http.get<Recipe[]>(`${this.baseUrl}/containing/${searchterm}`,this.httpOptions);
  }

  addRecipeSteps(newrecipesteps:any): Observable<Recipe[]> {
    return this.http.post<Recipe[]>(`${this.baseUrl}/submitRecipeSteps`, newrecipesteps,this.httpOptions);
  }


}
