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
  changePassword(data: any): Observable<any> {
      return this.http.put<any>("/changepass", data).pipe(
        //send username as param
      map((res) => {
        return res;
      }),
      catchError((error: any): Observable<any> => {
        return throwError(error);
      })
    );
  } 
}
