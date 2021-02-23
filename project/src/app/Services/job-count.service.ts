import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class JobCountService {
  private API_URL: string;

  constructor(private httpClient: HttpClient) { 
    //this.API_URL ="http://localhost:9099";
    this.API_URL ="http://localhost:9000/analyticsService";
  }

  applyJobCounter(payload:object): Observable<any>{
    return (this.httpClient.post(`${this.API_URL}/incrementCounter`, payload));

   }

   getAppliedJobsCounter(): Observable<any>{
    return (this.httpClient.get(`${this.API_URL}/showAllAnalytics`));
   }
}
