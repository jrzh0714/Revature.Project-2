import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';


@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  searchResult!:Recipe[]|null;

  constructor(private recipeSerivce: RecipeService) { }

  ngOnInit(): void {
    this.getResult().then((response)=>{
      this.searchResult = response;
      console.log(this.searchResult);
    });
  }

  async getResult() {
    const searchterm = "Mushroom";

    if (searchterm) {

      console.log(searchterm);
      return await this.recipeSerivce.getRecipesByName(searchterm).toPromise();
    } else {
      console.log("no searchterm");

      return null;
    }
  }

}
