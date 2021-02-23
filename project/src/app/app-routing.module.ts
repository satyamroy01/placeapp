import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { DashboardComponent } from './dashboard/dashboard.component';
import { JobDetailsComponent } from './dashboard/job-details/job-details.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegistrationComponent } from './registration/registration.component';
import {AuthGuardService as authguard} from './Services/auth-guard.service';
import { FavJobComponent } from './fav-job/fav-job.component';
import { AppliedjobComponent } from './appliedjob/appliedjob.component';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchComponent } from './search/search.component';
const routes: Routes = [
  {
    path:'home',
    component:HomeComponent
  },
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'search',
    component: SearchComponent
  },
  {
    path: 'search_results',
    component:SearchResultsComponent,
    children:[
      {
        path: 'jobdetails/:jobId',
        component:JobDetailsComponent,
        outlet:'jobDetailsOutlet'
      }
    ]
  },
  {
    path: 'appliedjob',
    component: AppliedjobComponent,
    canActivate: [authguard],
  },
  {
    path: 'fav-job',
    component: FavJobComponent,
    canActivate: [authguard],
  },
  {
    path: 'registration',
    component: RegistrationComponent
  },
  {
    path: 'dashboard',
    component: DashboardComponent,
    canActivate: [authguard],
    
    //canActivate:[MovieCanActivateGuard]
  },
  {
    path: '',
    component: HomeComponent,
    pathMatch:'full'
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
