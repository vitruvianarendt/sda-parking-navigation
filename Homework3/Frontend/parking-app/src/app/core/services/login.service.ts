import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { catchError, map } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginService {
  constructor(private http: HttpClient) { }
  logIn(data: any): Observable<any> {
    return this.http.post<any>('/login', data).pipe(
      map((res) => {
        return res;
      }),
      catchError((error: any): Observable<any> => {
        return throwError(error);
      })
    );
  }
}
