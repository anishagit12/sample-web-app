export interface User{
  name: string;
  email: string;
  stateName: string;
}

import { Component, OnInit } from '@angular/core';
import{ UserService } from '../user.service';
import { error } from 'console';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-user-form',
  templateUrl: './user-form.component.html',
  styleUrls: ['./user-form.component.css']
})

export class UserFormComponent implements OnInit{

  user = {name:'', email:'', stateName:''};
  successMessage: string = '';
  showSuccessMessage = false;
  fadeOutClass: boolean = false; 
  states: any[] = []; //Array to store the states

  constructor(private userService: UserService) {}

  ngOnInit(): void {
      this.fetchStates();
  }

  fetchStates(){
    this.userService.getAllStates().subscribe({
      next: (response) => {
        console.log('States fetched:', response);
        this.states = response; // Store received data in the states array
      },
      error: (error) =>{
        console.error('Error fetching states: ', error);
      }
    });
  }

  saveUser(userForm: NgForm){
    console.log('Submitting form with user:', this.user);
    this.userService.createUser(this.user).subscribe({
      next: (response) => {
        //console.log('Response:', response);
        this.successMessage = "✔️ User saved successfully";
        this.showSuccessMessage = true;
        this.fadeOutClass = false;
        
        userForm.resetForm();

        setTimeout(() => {
          this.fadeOutClass = true; // Start fade-out effect
          setTimeout(() => {
            this.showSuccessMessage = false; // Hide message completely
          }, 1000); // Wait for the fade effect to finish
        }, 3000);
      },
      error: (error) => {
        //console.error('Error saving user:', error);
        this.successMessage = 'Error saving user';
      },
      complete: () => {
        console.log('User creation process complete');

      }
    });
  }

}