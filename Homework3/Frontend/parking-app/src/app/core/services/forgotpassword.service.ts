import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, map, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ForgotpasswordService {

  constructor(private http: HttpClient) { }
  forgotPassword(data: any): Observable<any> {
    return this.http.post<any>("", data).pipe(
      map((res: any) => {
       return res;
      }),
      catchError((error: any): Observable<any> => {
        return throwError(error);
      })
    );
  }
}
