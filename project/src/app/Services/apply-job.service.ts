import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ApplyJobService {
  private API_URL: string;
  public myAppliedJobSvcArray:Array<any>;

  constructor(private httpClient: HttpClient) {
   // this.API_URL ="http://localhost:9097/api"
    this.API_URL ="http://localhost:9000/appliedJobService/api"
   }

   applyJob(payload:object): Observable<any>{
    const tok=sessionStorage.getItem("token");
    return (this.httpClient.post(`${this.API_URL}/saveUserAppliedJob`, payload,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`)
    }
    ));

   }

   getAppliedJobs(): Observable<any>{
    const tok=sessionStorage.getItem("token");
    return (this.httpClient.get(`${this.API_URL}/showAppliedJobs`,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`)
    }
    
    ));
   }

   getMyAppliedJobs(userId:string): Observable<any>{
    const tok=sessionStorage.getItem("token");
    return (this.httpClient.get(`${this.API_URL}/viewItems/${userId}`,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${tok}`)
    }
    ));
   }

   deleteMyAppliedJobs(userId:string,jobId:number): Observable<any>{
    const tok=sessionStorage.getItem("token");
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return (this.httpClient.delete(`${this.API_URL}/removeJobFromApplied/${userId}/${jobId}`,
    { headers:new HttpHeaders().set('Authorization', `Bearer ${tok}`), responseType: 'text'}));
   }
}