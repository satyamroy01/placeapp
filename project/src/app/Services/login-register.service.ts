import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ErrorHandlerService } from './error-handler.service';

@Injectable({
  providedIn: 'root'
})
export class LoginRegisterService {
  constructor(private httpcli: HttpClient) {}

  // Function to send login data to the backend
  // and error handling using erro-handler.ts
  login(payload: object): Observable<any> {
    return ( this.httpcli.post('http://localhost:8761/auth/login', payload) );
  }

  // Function to send register data to the backend
  // and error handling using erro-handler.ts
  register(payload: object): Observable<any> {
    return ( this.httpcli.post('http://localhost:8761/auth/register', payload) );
  }
}

