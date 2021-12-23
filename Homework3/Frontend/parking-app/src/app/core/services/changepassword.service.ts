import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { map, catchError } from 'rxjs/operators';
import { environment } from 'src/environments/environment';

@Injectable({
  providedIn: 'root'
})
export class ChangepasswordService {

  constructor(private http: HttpClient) { }
  changePassword(data: any, token: any): Observable<any> {
  //  const header = { 'Authorization': `Token ${token}`};
      return this.http.put<any>("/changepass", data).pipe(
      map((res) => {
        return res;
      }),
      catchError((error: any): Observable<any> => {
        return throwError(error);
      })
    );
  } 
}
