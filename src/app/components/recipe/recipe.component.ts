import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-recipe',
  templateUrl: './recipe.component.html',
  styleUrls: ['./recipe.component.css']
})
export class RecipeComponent implements OnInit {

  recipe!: Recipe|null;


  constructor(    private route: ActivatedRoute,
    private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.getRecipe().then((response)=>{
      this.recipe = response;
      console.log(this.recipe);
    });
  }

  async getRecipe() {
    const id = Number(this.route.snapshot.paramMap.get('id'));
    //TODO: replace null with service call

    if (id) {
      console.log(id);
      return await this.recipeService.getRecipe(id).toPromise();
    } else {
      console.log("no id");

      return null;
    }
  }


}
