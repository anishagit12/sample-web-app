import { Component } from '@angular/core';
import { NgForm } from '@angular/forms';
import { UserService } from '../user.service';
import { User } from '../user-form/user-form.component';
import { Router } from '@angular/router';

@Component({
  selector: 'app-show-by-id',
  templateUrl: './show-by-id.component.html',
  styleUrls: ['./show-by-id.component.css']
})
export class ShowByIDComponent{

  userId: string='';
  user: User | null=null;
  errorMessage: string='';

  constructor( private userService: UserService, private router: Router) { }

  fetchById(){

    const id = Number(this.userId.trim());
    if(isNaN(id) || id<=0){
      this.errorMessage= 'Please a enter a valid ID';
      return;
    }
    
    this.userService.getUserById(id).subscribe({
      next: (data)=> {

        this.errorMessage= '';

        this.router.navigate(['/user-details', id]);
      },

      error: () => {
        this.user= null;
        this.errorMessage='User not found';
      }
    });
  }

}