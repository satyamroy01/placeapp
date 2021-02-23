
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { ComponentFixture, TestBed } from '@angular/core/testing';
// import { Component } from '@angular/core';
import { MatToolbarModule } from '@angular/material/toolbar';
import { RouterTestingModule } from '@angular/router/testing';

import { HeaderComponent } from './header.component';
import { ModalModule } from 'ngx-bootstrap/modal';
// Added the declaration of BlankComponent to be used for routing

fdescribe('HeaderComponent', () => {
  let component: HeaderComponent;
  let fixture: ComponentFixture<HeaderComponent>;
  // let debugElement: any;
  beforeEach(async() => {
      TestBed.configureTestingModule({
      declarations: [ HeaderComponent],
      imports: [
        ReactiveFormsModule,
        RouterTestingModule.withRoutes([]),
        HttpClientModule,
        MatToolbarModule,
        ModalModule.forRoot()
      ],
    }).compileComponents();
    
  });
   
  beforeEach(() => {
    fixture = TestBed.createComponent(HeaderComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
    component.ngOnInit();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('Navbar should exist',()=>{
    let navbarobj=fixture.debugElement.query(By.css(".navbar"));
    expect(navbarobj).toBeTruthy();
});

it('Title PlaceAPP should exist',()=>{
  let titleobj=fixture.debugElement.query(By.css(".navbar-brand"));
  expect(titleobj).toBeTruthy();
});

  it(' Home navigation should exist',()=>{
    let navobj=fixture.debugElement.query(By.css(".nav-link"));
    expect(navobj).toBeTruthy();
});

it(' Findjobs navigation should exist',()=>{
  let navobj=fixture.debugElement.query(By.css(".nav-link"));
  expect(navobj).toBeTruthy();
});

it(' About navigation should exist',()=>{
  let navobj=fixture.debugElement.query(By.css(".nav-link"));
  expect(navobj).toBeTruthy();
});

it(' Login navigation should exist',()=>{
  let navobj=fixture.debugElement.query(By.css(".nav-link"));
  expect(navobj).toBeTruthy();
});

it('Register navigation should exist',()=>{
  let navobj=fixture.debugElement.query(By.css(".nav-link"));
  expect(navobj).toBeTruthy();
});
});

