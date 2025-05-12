import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { FormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { UserFormComponent } from './user-form/user-form.component';
import { HomepageComponent } from './homepage/homepage.component';
import { UsersListComponent } from './users-list/users-list.component';
import { ShowByIDComponent } from './show-by-id/show-by-id.component';
import { DisplayUserByIdComponent } from './display-user-by-id/display-user-by-id.component';

@NgModule({
  declarations: [
    AppComponent,
    UserFormComponent,
    HomepageComponent,
    UsersListComponent,
    ShowByIDComponent,
    DisplayUserByIdComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
