import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, map, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ParkingsService {
  constructor(public http: HttpClient) {}
  getParkingNames() : Observable<any> {
    return this.http.get<any>('http://localhost:8080/api/v1/parking').pipe(
      map((res: any) => {
        console.log(res);
       return res ;
      }),
      catchError((error: any): Observable<any> => {
        return throwError(error);
      })
    );
  }
}
