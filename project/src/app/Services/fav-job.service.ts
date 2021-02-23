import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class FavJobService {

  private API_URL: string;
  public myFavJobSvcArray:Array<any>;
  constructor(private httpClient:HttpClient) { 

   // this.API_URL ="http://localhost:9098/api";
    this.API_URL="http://localhost:9000/favouriteService/api";
  }

  addFavJob(payload:object): Observable<any>{
    const token=sessionStorage.getItem("token");
    return (this.httpClient.post(`${this.API_URL}/saveUserFav`, payload,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
    }
    ));

   }

   getFavJobs(): Observable<any>{
    const token=sessionStorage.getItem("token");
    return (this.httpClient.get(`${this.API_URL}/showFavJobs`,
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
    }
    
    ));
   }

   getMyFavJobs(userId:string): Observable<any>{
    const token=sessionStorage.getItem("token");

    return (this.httpClient.get(`${this.API_URL}/viewItems/${userId}`,
    
    {
      headers: new HttpHeaders().set('Authorization', `Bearer ${token}`)
    }
    ));
   }

   deleteMyFavJobs(userId:string,jobId:number): Observable<any>{
    const token=sessionStorage.getItem("token");
    const headers = new HttpHeaders().set('Content-Type', 'text/plain; charset=utf-8');
    return (this.httpClient.delete(`${this.API_URL}/removeJobFromFavourites/${userId}/${jobId}`,
    { headers:new HttpHeaders().set('Authorization', `Bearer ${token}`), responseType: 'text'}));
   }
}
