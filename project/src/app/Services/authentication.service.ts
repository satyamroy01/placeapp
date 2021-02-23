import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { CanActivate, Router } from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService{
  private API_URL: string;
 // class to implement route guard for particular routes
  constructor(public router: Router,private httpClient: HttpClient) {
    //this.API_URL ="http://localhost:9095"
    this.API_URL ="http://localhost:9000/jwtauthentication"
  }

  login(payload: object): Observable<any> {

    return (this.httpClient.post(`${this.API_URL}/placeapp/auth/login`, payload));
  }

  register(payload: object): Observable<any> {

    return (this.httpClient.post(`${this.API_URL}/api/placeapp/adduser`, payload));
  }

  // favrioute(payload : object) : Observable<any>{

  //   return(this.httpClient.post('${this.API_URL}/placeapp/auth/favrioute',payload));
  // }



}
