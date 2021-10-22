import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/models/user';
import { UserService } from 'src/app/services/user.service';
import { ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-user',
  templateUrl: './user.component.html',
  styleUrls: ['./user.component.css']
})
export class UserComponent implements OnInit {

  user!:User|null;
  constructor(private route: ActivatedRoute,
    private userService: UserService) { }

  ngOnInit(): void {
    this.getUserByUsername().then((response)=>{
      this.user = response;
      console.log(this.user);
    });
  }

  async getUserByUsername() {
    const username = String(this.route.snapshot.paramMap.get('username'));
    //TODO: replace null with service call

    if (username) {

      console.log(username);
      return await this.userService.getUserByUsername(username).toPromise();
    } else {
      console.log("no username");

      return null;
    }
  }
}
