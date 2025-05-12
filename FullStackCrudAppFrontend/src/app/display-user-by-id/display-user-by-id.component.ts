import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../user.service';
import { User } from '../user-form/user-form.component';

@Component({
  selector: 'app-display-user-by-id',
  templateUrl: './display-user-by-id.component.html',
  styleUrls: ['./display-user-by-id.component.css']
})
export class DisplayUserByIdComponent implements OnInit{

  user: User | null=null;
  errorMessage='';

  constructor( private route: ActivatedRoute, private userService: UserService) { }

  ngOnInit(): void {

    const id = Number(this.route.snapshot.paramMap.get('id'));
    this.userService.getUserById(id).subscribe({
      next: (data) => this.user = data,
      error: () => this.errorMessage = 'user not found'
    });
  }



}
