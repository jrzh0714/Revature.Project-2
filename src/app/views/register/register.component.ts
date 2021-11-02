import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {
  registerForm!: FormGroup;
    loading = false;
    submitted = false;
    returnUrl!: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private router: Router,
        private userService:UserService
    ) {
    }

    ngOnInit() {
        this.registerForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required],           
            email: ['', Validators.required],
            firstname: ['', Validators.required],
            lastname: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.registerForm.controls; }

    onSubmit() {
        console.log("username: "+this.f.username.value);
        console.log("pw: "+this.f.password.value);
        console.log("email: "+this.f.email.value);
        console.log("firstname: "+this.f.firstname.value);
        console.log("lastname: "+this.f.lastname.value);

        var newuser:User = {
            username:this.f.username.value,
            hash:this.f.password.value,
            email:this.f.email.value,
            firstname:this.f.firstname.value,
            lastname:this.f.lastname.value,
            avatarURL:'',
            userRecipeList:[]
          };
          this.userService.register(newuser).toPromise()
          .then((res) =>{
            console.log(res);
            alert("New user registered.");

            window.location.href = 'login';

          },
          (error)=>{
            console.log(error);
            setTimeout(() =>{
              alert("Username is taken.");
              window.location.href = 'register';
            },1000);  
          }
          );

    }
}