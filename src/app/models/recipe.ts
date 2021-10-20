import { RecipeStep } from 'src/app/models/recipeStep';

export interface Recipe {
    id: number;
    name: string;
    rating: number;
    recipeSteps:RecipeStep[];
    thumbnail: number;
    likes: number;
    view_count: number;
    publish_date:Date;
    difficulty: number;

}