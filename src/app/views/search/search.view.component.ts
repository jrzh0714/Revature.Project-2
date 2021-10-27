import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-search-view',
  templateUrl: './search.view.component.html',
  styleUrls: ['./search.view.component.css']
})
export class SearchViewComponent implements OnInit {

  searchResult!:Recipe[]|null;
  constructor(private route: ActivatedRoute,
    private recipeSerivce: RecipeService) { }

  ngOnInit(): void {
    this.getResult().then((response)=>{
      this.searchResult = response;
      console.log(this.searchResult);
    });
  }

  async getResult() {
    const searchterm = String(this.route.snapshot.paramMap.get('searchterm'));

    if (searchterm) {

      console.log(searchterm);
      return await this.recipeSerivce.getRecipesByName(searchterm).toPromise();
    } else {
      console.log("no searchterm");

      return null;
    }
  }
}
