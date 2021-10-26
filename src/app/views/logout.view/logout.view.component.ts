import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { localeData } from 'moment';
import { Subject } from 'rxjs';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-logout.view',
  templateUrl: './logout.view.component.html',
  styleUrls: ['./logout.view.component.css']
})
export class Logout implements OnInit {

  constructor(private router:Router,private authService:AuthService) { 
    sessionStorage.clear();
    localStorage.clear();
    
    setTimeout(() =>{
    
      console.log("redirecting");
      window.location.href = '';

    },1000);
  }

  ngOnInit(): void {
    
  }

}
