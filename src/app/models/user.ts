import { Recipe } from 'src/app/models/recipe';

export interface User {
    username:string;
    hash:string;
    firstname:string;
    lastname:string;
    email:string;
    avatarURL:string;
    userRecipeList:Recipe[];
}
