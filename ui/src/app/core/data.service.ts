import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {IFootball, IMatch} from './../shared/interfaces';
import { map, catchError } from 'rxjs/operators';

@Injectable()
export class DataService {

    constructor(private http: HttpClient){}

    getFootballClubs(): Observable<IFootball[]> {
        return this.http.get<IFootball[]>('/clubs/list')
            .pipe(
                catchError(DataService.handleError)
            );
    }

    getMatches(): Observable<IMatch[]> {
      return this.http.get<IMatch[]>('/matches/list')
        .pipe(
          catchError(DataService.handleError)
        );
    }

    getRandomMatch(): Observable<IMatch> {
      return this.http.get<IMatch>('/matches/addRandom')
        .pipe(
          catchError(DataService.handleError)
        );
    }

    match(): Promise<any> {
      return this.http.get('/matches/list').toPromise();
    }

    private static handleError(error: any){
        console.error("Server error", error);
        if(error.error instanceof Error){
            const errorMessage = error.error.message;
            return Observable.throw(errorMessage);
        }
        return Observable.throw(error || "Server Error");
    }


}
