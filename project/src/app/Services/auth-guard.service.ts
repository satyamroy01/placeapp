import { Injectable } from '@angular/core';
import {​​​​​​​ ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot, UrlTree }​​​​​​​ from '@angular/router';
import {​​​​​​​ Observable }​​​​​​​ from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AuthGuardService implements CanActivate {​​​​​​​
  constructor(public router: Router) {​​​​​​​ }​​​​​​​
  // Implement 'canActivate' method for Route Guard
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {​​​​​​​
    let result: boolean;
    let val = sessionStorage.getItem("token");
    if (val) {​​​​​​​
      return true;
    }​​​​​​​
    else {​​​​​​​
      this.router.navigateByUrl('/login');
      return false;
    }​​​​​​​
  }​​​​​​​
}​​​​​​​