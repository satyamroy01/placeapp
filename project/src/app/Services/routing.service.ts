import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { BehaviorSubject } from 'rxjs';
@Injectable({
  providedIn: 'root'
})
export class RoutingService {

  modalClose = new BehaviorSubject<string>(null);

  constructor(private routeobj : Router) { }

  openHome(){
    this.routeobj.navigate(['home']);

  }
  openDashboard(){
    this.routeobj.navigate(['dashboard']);

  }
  
  openLogin(){
    this.routeobj.navigate(['login']);

  }
  openRegister(){
    this.routeobj.navigate(['registration']);
}
openSearchResults(){
  this.routeobj.navigate(['search_results']);
}
openJob(jobId){
  this.routeobj.navigate(['search_results',{
    outlets: {
      jobDetailsOutlet : ['jobdetails',jobId]
    }
  }])
}
}