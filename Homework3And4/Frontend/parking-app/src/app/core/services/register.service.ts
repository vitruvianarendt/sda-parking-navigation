import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root'
})
export class RegisterService {
  constructor(private http: HttpClient, private router: Router) { }
  register(data: any): Observable<any> {
    return this.http.post<any>("http://localhost:8080/api/v1/registration/", data).pipe(
      map((res) => {
       this.router.navigate(['/auth/login']);
       return res;
      }),
      catchError((error: any): Observable<any> => {
        this.router.navigate(['/auth/login']);
        return throwError(error);
      })
    );
  }
}