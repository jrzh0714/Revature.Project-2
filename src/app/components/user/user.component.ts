import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';
import { Recipe } from 'src/app/models/recipe';
import { RecipeService } from 'src/app/services/recipe.service';
@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user!:User|null;
  likedrecipes!:Recipe[]|null;
  userid!:number|null;
  display:string = "recipes";
  constructor(private route: ActivatedRoute,
    private userService: UserService,
    private recipeService: RecipeService) { }

  ngOnInit(): void {
    this.getUserByUsername().then((response:any)=>{
      this.user = response;
      this.userid = response.user_id;
      console.log(this.user);
      this.getLikedRecipes().then((response:Recipe[]|null)=>{
        this.likedrecipes = response;
        console.log(this.likedrecipes);
      })
    });
    
    
  }

  showrecipes(event:any){

    this.display ="recipes";
  }
  showliked(event:any){

    this.display ="likes";

  }
  async getUserByUsername() {
    const username = String(this.route.snapshot.paramMap.get('username'));

    if (username) {

      console.log(username);
      return await this.userService.getUserByUsername(username).toPromise();
    } else {
      console.log("no username");

      return null;
    }
  }
  async getLikedRecipes() {
    const userid:number|null = this.userid;

    if (userid) {

      console.log(userid);
      return await this.recipeService.likedRecipes(userid).toPromise();
    } else {
      console.log("no userid");

      return null;
    }
  }
}
