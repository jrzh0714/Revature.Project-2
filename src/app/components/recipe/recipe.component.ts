import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
import { UserService } from 'src/app/services/user.service';

import { ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { Observable } from 'rxjs';
import * as base64 from "byte-base64";
import { Router } from '@angular/router';
import { Location } from '@angular/common'

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  recipe!: Recipe|null;
  imageSrc!:string;
  convertedDate!:String;
  recipeAuthorUsername!: String;
  constructor(
    private route: ActivatedRoute,
    private recipeService: RecipeService,
    private userService: UserService,
    private router: Router,
    private location: Location) { }

  ngOnInit(): void {
    this.getRecipe().then((response)=>{
      this.recipe = response;
      if (response){
        this.convertedDate = this.convertDate(response.publishDate);
        this.getUsernameById(response.userId);
      }
      console.log(this.recipe);
      console.log(this.recipeAuthorUsername);

      if (this.recipe){
        this.imageSrc = "data:image/png;base64,"+this.recipe.thumbnail;
      }

    });

  }

  back(){
    this.location.back();
  }
  async getRecipe() {
    const id = Number(this.route.snapshot.paramMap.get('id'));

    if (id) {
      console.log(id);
      return await this.recipeService.getRecipe(id).toPromise();
    } else {
      console.log("no id");

      return null;
    }
  }

  getUsernameById(userid:number) {
    if (userid) {
      console.log(userid);
        this.userService.getUsernameById(userid).subscribe((res)=>{
          this.recipeAuthorUsername = res;
        });
    } else {
      console.log("no userid");
    }

  }
  convertDate(date:Date):string{
    let date1:Date = new Date(date);
    return date1.toLocaleDateString('en', { year: 'numeric', month: 'short', day: '2-digit' });
  }


}
