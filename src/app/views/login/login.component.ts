import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { first } from 'rxjs/operators';
import { AuthService } from 'src/app/services/auth.service';
import { LoginAttempt } from 'src/app/models/loginattempt'; 
import { UserService } from 'src/app/services/user.service';
@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})


export class LoginComponent implements OnInit {
    loginForm!: FormGroup;
    loading = false;
    submitted = false;
    returnUrl!: string;

    constructor(
        private formBuilder: FormBuilder,
        private route: ActivatedRoute,
        private authservice: AuthService,
        private userService: UserService,
        private router: Router
    ) {
    }

    ngOnInit() {
        this.loginForm = this.formBuilder.group({
            username: ['', Validators.required],
            password: ['', Validators.required]
        });

        // get return url from route parameters or default to '/'
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || '/';
    }

    // convenience getter for easy access to form fields
    get f() { return this.loginForm.controls; }

    onSubmit() {
        console.log("username: "+this.f.username.value);
        console.log("pw: "+this.f.password.value);
        var credentials:LoginAttempt = {
          username:this.f.username.value,
          password:this.f.password.value
        };
        this.authservice.login(credentials).toPromise().then((response:any)=>
        {
          var token = response.token;
          sessionStorage.setItem('access-token',token)
          if(token != null){
            sessionStorage.setItem('username',credentials.username)
            this.userService.getUserByUsername(credentials.username).toPromise().then((res)=>{
              console.log(res);
              setTimeout(() =>{
                console.log("login redirect");
                window.location.href = '';
              },1000);
            });
  
          }
        }, (error)=>{
          console.log(error);
          setTimeout(() =>{
            alert("Login invalid, try again.");
            window.location.href = 'login';
          },1000);  
        }
      );;
        

    }
}