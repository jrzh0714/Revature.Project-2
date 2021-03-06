import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { RecipeService } from 'src/app/services/recipe.service';
import { Recipe } from 'src/app/models/recipe';
import * as moment from 'moment';
import { ElementRef } from '@angular/core';
import { RecipeStep } from 'src/app/models/recipeStep';

@Component({
  selector: 'app-recipeform',
  templateUrl: './recipeform.component.html',
  styleUrls: ['./recipeform.component.css']
})

export class RecipeformComponent implements OnInit {
    recipeForm!: FormGroup;
    user_id!:number;
    loading = false;
    submitted = false;
    returnUrl!: string;
    imageSrc!:string;
    step:number = 0;
    recipeSteps:any[] = [];
    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService:UserService,
        private recipeService:RecipeService,
        private elementRef:ElementRef

    ) {
    }

    ngOnInit() {
        this.recipeForm = this.formBuilder.group({
          recipename: ['', Validators.required],
            stepdesc: ['', Validators.required],
            recipeimage: ['', Validators.required],
            recipediff: ['', Validators.required]
            

        });
        this.getUserByUsername(sessionStorage.getItem("username")).then((response:any)=>{
          this.user_id = response.user_id;
          console.log(this.user_id);
        });
        console.log(moment(new Date()).toString());
        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    async getUserByUsername(username:string|null) {
  
      if (username) {
  
        return await this.userService.getUserByUsername(username).toPromise();
      } else {
        console.log("no username");
  
        return null;
      }
    }


    // convenience getter for easy access to form fields
    get f() { return this.recipeForm.controls; }

    addnewstep() {
      console.log("newstep "+this.step);
      this.step +=1;
      var newStep={
        stepDescription:"",
        stepNumber:this.step,
        stepIngredients:[]
      }
      this.recipeSteps.push(newStep);
    }
    removestep() {
      this.step -=1;
      this.recipeSteps.pop();

    }
    addnewingredient(stepnum:number){
      console.log(stepnum);
      var newIngredient={
        ingredientId:1,
        ingredientname:null,
        weight:null,
        weightUnit:null,
        volume:null,
        volumeUnit:null
      }
      this.recipeSteps[stepnum-1].stepIngredients.push(newIngredient);
      console.log(this.recipeSteps[stepnum-1]);
    }
    removeingredient(stepnum:number){
      console.log(stepnum);
      this.recipeSteps[stepnum-1].stepIngredients.pop();

      console.log(this.recipeSteps[stepnum-1]);
    }
    onSubmit() {
        console.log("recipename: "+this.f.recipename.value);
        console.log("stepdesc: "+this.f.stepdesc.value);
        console.log("recipeimage: "+this.imageSrc);

        console.log("recipediff: "+this.f.recipediff.value);
        var newrecipe = {
          userId:this.user_id,
          name: this.f.recipename.value,
          rating: 0,
          recipeSteps:this.recipeSteps,
          thumbnail: this.imageSrc.split(',')[1],
          likes: 0,
          viewCount: 0,
          publishDate: moment(new Date()).format("YYYY-MM-DD").toString(),
          difficulty: this.f.recipediff.value
        };
        console.log(newrecipe);

        this.recipeService.addRecipe(newrecipe).toPromise()
        .then((res:any) =>{
          console.log(res);
          window.location.href = 'recipe/'+res.id;

        }, (error)=>{
          alert("Please fill out all required fields.");  
        });

    }
    onFileChange(event:any) {
      const reader = new FileReader();
      
      if(event.target.files && event.target.files.length) {
        const [file] = event.target.files;
        reader.readAsDataURL(file);
      
        reader.onload = () => {
          this.imageSrc = reader.result as string;
       
          this.recipeForm.patchValue({
            fileSource: reader.result
          });
     
        };
     
      }
    }
}