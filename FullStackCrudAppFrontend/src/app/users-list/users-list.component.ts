import { Component, OnInit } from '@angular/core';
import { UserService } from '../user.service';
import { PaginatedResponse } from '../user.service';

@Component({
  selector: 'app-users-list',
  templateUrl: './users-list.component.html',
  styleUrls: ['./users-list.component.css']
})
export class UsersListComponent implements OnInit {

  users: any[] = [];
  currentPage=0;
  pageSize = 15;
  totalPages: number=0;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.fetchUsers();
  }

  fetchUsers(){
    this.userService.getAllUsers(this.currentPage, this.pageSize).subscribe({
      next: (response: PaginatedResponse) => {
        this.users = response.content;
        this.totalPages = response.totalPages;
      },
      error: (error) => {
        console.error('Error fetching users: ', error);
      }
    });
  }

  nextPage(){
    if(this.currentPage < this.totalPages -1 ){
      this.currentPage++;
      this.fetchUsers();
    }
  }

  prevPage(){
    if(this.currentPage>0){
      this.currentPage--;
      this.fetchUsers();
    }
  }

}
