import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from '../../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http: HttpClient) { }
  register(data: any): Observable<any> {
    return this.http.post<any>("/register", data).pipe(
      //`${environment.apiUrl}/register`
      map((res) => {
       return res;
      }),
      catchError((error: any): Observable<any> => {
        return throwError(error);
      })
    );
  }
}