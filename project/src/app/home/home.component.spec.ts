import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { MatExpansionModule } from '@angular/material/expansion';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { Router } from '@angular/router';
import { RouterTestingModule } from '@angular/router/testing';
import { ModalModule } from 'ngx-bootstrap/modal';
import { NgxPaginationModule } from 'ngx-pagination';
import { AppliedjobComponent } from '../appliedjob/appliedjob.component';
import { DashboardComponent } from '../dashboard/dashboard.component';
import { FavJobComponent } from '../fav-job/fav-job.component';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';
import { LoginComponent } from '../login/login.component';
import { RegistrationComponent } from '../registration/registration.component';
import { RoutingService } from '../Services/routing.service';

import { HomeComponent } from './home.component';

fdescribe('HomeComponent', () => {
  let component: HomeComponent;
  let fixture: ComponentFixture<HomeComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule,NgxPaginationModule,MatExpansionModule,BrowserAnimationsModule,RouterTestingModule,ModalModule.forRoot()],
      declarations: [ HomeComponent,
        RegistrationComponent,
        DashboardComponent,
        HeaderComponent,
        FooterComponent,
        LoginComponent,
        AppliedjobComponent,
        FavJobComponent
       ],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(HomeComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

   it('should have show login check function',()=>{
    expect(component.showLogin).toBeTruthy();
  });

  it('should have showRegister check function',()=>{
    expect(component.showRegister).toBeTruthy();
  });

  it('should have getLocations check function',()=>{
    expect(component.getLocations).toBeTruthy();
  });

  it('should have selectCategory check function',()=>{
    expect(component.selectCategory).toBeTruthy();
  });

  it('should have search check function',()=>{
    expect(component.search).toBeTruthy();
  });
});
