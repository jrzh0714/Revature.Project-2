import { RecipeStep } from 'src/app/models/recipeStep';

export interface Recipe {
    id: number;
    userId:number;
    name: string;
    rating: number;
    recipeSteps:RecipeStep[];
    thumbnail: number;
    likes: number;
    viewCount: number;
    publishDate:Date;
    difficulty: number;

}