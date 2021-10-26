import { RecipeStep } from 'src/app/models/recipeStep';

export interface Recipe {
    id: number |null;
    userId:number;
    name: string;
    rating: number;
    recipeSteps:RecipeStep[];
    thumbnail: string;
    likes: number;
    viewCount: number;
    publishDate:Date;
    difficulty: number;

}