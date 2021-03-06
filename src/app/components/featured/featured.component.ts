import { Component, OnInit } from '@angular/core';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';

@Component({
  selector: 'app-featured',
  templateUrl: './featured.component.html',
  styleUrls: ['./featured.component.css']
})
export class FeaturedComponent implements OnInit {

  featured!: Recipe[];

  constructor(private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.getTrending().then((response)=>{
      this.featured = response;
    
      console.log(this.featured);

    });
  }

  async getTrending() {
    return await this.recipeService.getTrending().toPromise();
  }

}
