import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

import {HttpClientModule} from '@angular/common/http';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RouterModule, Routes } from '@angular/router';
import {MatDialogModule} from '@angular/material/dialog';
import { FlexLayoutModule } from '@angular/flex-layout';
import {NgxPaginationModule} from 'ngx-pagination';

import { CommonModule } from '@angular/common';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { RegistrationComponent } from './registration/registration.component';
import { JobDetailsComponent } from './dashboard/job-details/job-details.component';
import { MatTableModule } from '@angular/material/table';
import { MatPaginatorModule } from '@angular/material/paginator';
import { MatSortModule } from '@angular/material/sort';
import { FavJobComponent } from './fav-job/fav-job.component';
import { AppliedjobComponent } from './appliedjob/appliedjob.component';
import { ParentComponent } from './parent/parent.component';
import { ModalModule } from 'ngx-bootstrap/modal';
import { SearchResultsComponent } from './search-results/search-results.component';
import { SearchComponent } from './search/search.component';

import {MatSnackBarModule} from '@angular/material/snack-bar';



// const myRoutes: Routes = [
//   {
//     path: 'login',
//     component: LoginComponent
//   },
//   {
//     path: 'registration',
//     component: RegistrationComponent
//   },
//   {
//     path: 'appliedjob',
//     component: AppliedjobComponent
//   },
//   {
//     path: 'fav-job',
//     component: FavJobComponent
//   },
//   {
//     path: 'dashboard',
//     component: DashboardComponent,
//     // canActivate: [UstcanactivateGuard]
//   },
//   {
//     path: '',
//     redirectTo: 'login',
//     pathMatch: 'full'

//   }
// ]

@NgModule({
  declarations: [
    AppComponent,
    DashboardComponent,
    HeaderComponent,
    FooterComponent,
    HomeComponent,
    LoginComponent,
    RegistrationComponent,
    JobDetailsComponent,
    FavJobComponent,
    AppliedjobComponent,
    ParentComponent,
    SearchResultsComponent,
    SearchComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    BrowserAnimationsModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    // RouterModule.forRoot(myRoutes) ,
    RouterModule,
    MatDialogModule,
    CommonModule,
    MatExpansionModule,
    MatInputModule,
    MatFormFieldModule,
    MatButtonModule,
    FlexLayoutModule,
    MatToolbarModule,
    MatCardModule,
    MatSnackBarModule,
    NgxPaginationModule,
    MatIconModule,
    MatTableModule,
    MatPaginatorModule,
    MatSortModule,
    ModalModule.forRoot()
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
