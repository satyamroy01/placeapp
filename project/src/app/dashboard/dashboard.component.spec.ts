import { HttpClientModule } from '@angular/common/http';
import { async, ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { MatExpansionModule } from '@angular/material/expansion';
import { By } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { NgxPaginationModule } from 'ngx-pagination';
import { FooterComponent } from '../footer/footer.component';
import { HeaderComponent } from '../header/header.component';
import { HomeComponent } from '../home/home.component';
import { ApplyJobService } from '../Services/apply-job.service';
import { FavJobService } from '../Services/fav-job.service';
import { JobService } from '../Services/job.service';
import { RoutingService } from '../Services/routing.service';
import { DashboardComponent } from './dashboard.component';
fdescribe('DashboardComponent', () => {
  let component: DashboardComponent;
  let fixture: ComponentFixture<DashboardComponent>;
  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [HttpClientModule,RouterTestingModule,NgxPaginationModule,MatExpansionModule,BrowserAnimationsModule],
      declarations: [ DashboardComponent,HeaderComponent,FooterComponent,HomeComponent ],
      providers:[JobService,ApplyJobService,FavJobService]
    })
    .compileComponents();
  });
  beforeEach(() => {
    fixture = TestBed.createComponent(DashboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });
  it('should create', () => {
    expect(component).toBeTruthy();
  });
  it('should have loggedIn check function',()=>{
    expect(component.loggedInCheck).toBeTruthy();
  });
  it('should have get dashboard data function',()=>{
    expect(component.getJobDataInDashboard).toBeTruthy();
  });
  it('should have get user applied job  function',()=>{
      expect(component.getUserAppliedJobs).toBeTruthy();
    });
  it('should have get userFav function',()=>{
    expect(component.getUserFavJobs).toBeTruthy();
  });
  it('should have get subscribeApplyBtn function',()=>{
    expect(component.subscribeApplyBtn).toBeTruthy();
  });
  it('should have get subscribeModalClose function',()=>{
    expect(component.subscribeModalClose).toBeTruthy();
  });
  // it('Mat Expansion panel should exist',()=>{
  //   let myexpansionpanel = fixture.debugElement.query(By.css('mat-expansion-panel'));
  //   expect(myexpansionpanel).toBeTruthy();
  // });
  // it('category cards should be displayed in expansion panel',()=>{
  //   let categoryCards = fixture.debugElement.query(By.css('#categoriesSection'));
  //   expect(categoryCards).toBeTruthy();
  // });
  // it('search button should exist inside category section',()=>{
  //   let mybutton = fixture.debugElement.query(By.css('#searchBtn'));
  //   expect(mybutton).toBeTruthy();
  // });
  // it('Job Grid should exist to display all jobs fetched from 3rd party API',()=>{
  //   let JobGrid = fixture.debugElement.query(By.css('#jobGrid'));
  //   expect(JobGrid).toBeTruthy();
  // });
  // it('should have addFav function'),waitForAsync( ()=>{
  //   expect(component.addFav).toBeTruthy();
  //  });
  // it('should have removeFav function'),waitForAsync( ()=>{
  //   expect(component.removeFav).toBeTruthy();
  // });
  // it('should have applyJob function'),waitForAsync( ()=>{
  //   expect(component.applyJob).toBeTruthy();
  // });
  // it('Mat Expansion panel should exist',()=>{
  //   let myexpansionpanel = fixture.debugElement.query(By.css('mat-expansion-panel'));
  //   expect(myexpansionpanel).toBeTruthy();
  // })
});


