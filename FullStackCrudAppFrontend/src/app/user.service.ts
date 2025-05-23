import { Injectable } from '@angular/core';
import{ HttpClient } from '@angular/common/http';
import { catchError, Observable, tap, throwError } from 'rxjs';
import { environment } from 'src/environments/environment';

export interface PaginatedResponse{
  content: any[];
  totalPages: number;
  totalElements: number;
  size: number;
  number: number;
}

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private apiUrl = `${environment.apiBaseUrl}/entityObj`; //base url of your API
  private statesUrl = `${environment.apiBaseUrl}/StateOps/getAllStates`;

  constructor(private http: HttpClient) {}

  //fetch all states to populate dropdown
  getAllStates(): Observable<any[]> {
    return this.http.get<any[]>(this.statesUrl).pipe(
      catchError(error => {
        console.error('Error fetching states: ', error);
        return throwError(() => new Error(error));
      })
    )
  }
  //create a user
  createUser(user: {name: string, email: string, stateName:string}): Observable<any>{
    //console.log('Making API request with user:', user);
    return this.http.post(`${this.apiUrl}/save`, user, {responseType: 'text'}).pipe(
      //tap((response: any) => console.log('Response from backend', response)),
      catchError(error => {
        console.error('Error occured: ', error);
        return throwError(() => new Error(error));
      }) 

    );

  }

  //get all users
  getAllUsers(page: number, size:number): Observable<PaginatedResponse>{
    return this.http.get<PaginatedResponse>(`${this.apiUrl}/getUsers?page=${page}&size=${size}`).pipe(
      catchError(error => {
        console.error('Error fetching users: ', error);
        return throwError(() => new Error(error));
      })
    );
  }

  //get a user by ID
  getUserById(id: number): Observable<any>{
    return this.http.get<any>(`${this.apiUrl}/getUser/${id}`);
  }

}
