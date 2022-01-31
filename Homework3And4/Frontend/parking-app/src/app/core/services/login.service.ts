import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient, private router: Router) { }
  logIn(data: any): Observable<any> {
    return this.http.post<any>('http://localhost:8080/api/v1/login/', data).pipe(
      map((res) => {
        this.router.navigate(['/profile']);
        return res;
      }),
      catchError((error: any): Observable<any> => {
        this.router.navigate(['/profile']);
        return throwError(error);
      })
    );
  }
}
