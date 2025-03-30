import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { UserFormComponent } from './user-form/user-form.component';
import { HomepageComponent } from './homepage/homepage.component';
import { UsersListComponent } from './users-list/users-list.component';

const routes: Routes = [
  { path: 'create-user', component: UserFormComponent}, // Route for the user form page
  { path: 'get-all-users', component: UserFormComponent},
  { path:'get-by-id', component: UserFormComponent},
  { path: '', component: HomepageComponent} ,// Default route
  { path: '', component: UserFormComponent},
  { path: 'users', component: UsersListComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {  }