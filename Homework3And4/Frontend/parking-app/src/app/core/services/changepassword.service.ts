import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class ChangepasswordService {

  constructor(private http: HttpClient, private router: Router) { }
  changePassword(data: any): Observable<any> {
      return this.http.put<any>("http://localhost:8080/api/v1/password/", data).pipe(
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
