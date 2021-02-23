import { HttpClientModule } from '@angular/common/http';
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatCardModule } from '@angular/material/card';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { MatToolbarModule } from '@angular/material/toolbar';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterTestingModule } from '@angular/router/testing';
import { NgxPaginationModule } from 'ngx-pagination';

import { SearchResultsComponent } from './search-results.component';

fdescribe('SearchResultsComponent', () => {
  let component: SearchResultsComponent;
  let fixture: ComponentFixture<SearchResultsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SearchResultsComponent ],
      imports: [FormsModule, HttpClientModule, RouterTestingModule,MatIconModule,MatTableModule,
        ReactiveFormsModule,MatFormFieldModule,MatCardModule,MatExpansionModule,MatIconModule,
        MatIconModule,MatToolbarModule,BrowserAnimationsModule,NgxPaginationModule],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(SearchResultsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have loggedInCheck check function',()=>{
    expect(component.loggedInCheck).toBeTruthy();
  });

  it('should have getUserAppliedJobs check function',()=>{
    expect(component.getUserAppliedJobs).toBeTruthy();
  });

  it('should have getUserFavJobs check function',()=>{
    expect(component.getUserFavJobs).toBeTruthy();
  });

  it('should have subscribeModalClose check function',()=>{
    expect(component.subscribeModalClose).toBeTruthy();
  });

});
