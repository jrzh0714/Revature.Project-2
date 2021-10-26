import { Component, OnChanges, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { AuthService } from 'src/app/services/auth.service';
@Component({
  selector: 'app-userinfo',
  templateUrl: './userinfo.component.html',
  styleUrls: ['./userinfo.component.css']
})
export class UserinfoComponent implements OnInit {

  username!:string|null;
  user!:User;
  constructor(private userService: UserService,private authService:AuthService) {
    this.username = sessionStorage.getItem("username");
  };

  ngOnInit(): void {

    console.log("test");

  }
  logout(){
    this.username=null;
  }
  
  
}
