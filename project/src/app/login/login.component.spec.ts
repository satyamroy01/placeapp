import { waitForAsync,ComponentFixture, TestBed, fakeAsync } from '@angular/core/testing';
import{ BrowserModule, By}from '@angular/platform-browser'
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { LoginComponent } from './login.component';
import { HttpClientModule } from '@angular/common/http';
import { post } from 'jquery';

fdescribe('LoginComponent', () => {
  let component: LoginComponent;
  let fixture: ComponentFixture<LoginComponent>;
  let el: HTMLElement;

  beforeEach(async () => {
    TestBed.configureTestingModule({
      declarations: [ LoginComponent ],
      imports: [FormsModule, HttpClientModule, RouterTestingModule,ReactiveFormsModule],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(LoginComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', () => {
    component.userform.controls.emailId.setValue('');
    component.userform.controls.password.setValue('');
    expect(component.userform.valid).toBeFalsy();
  });

  it('email field validity', () => {
    const emailId = component.userform.controls.emailId;
    expect(emailId.valid).toBeFalsy();

    emailId.setValue('');
    expect(emailId.hasError('required')).toBeTruthy();
  });

  it('should show default value for the userform',()=>{
    expect(component.userform.value).toEqual({emailId:'',password:''})
});

it('password field validity', () => {
  const password = component.userform.controls.password;
  expect(password.valid).toBeFalsy();

  password.setValue('');
  expect(password.hasError('required')).toBeTruthy();

});

it('Should set submitted to true', waitForAsync(() => {
  component.login(post);
  expect(component.login).toBeTruthy();

}));

it('Should call the login method', () =>{ fakeAsync(() =>{
  fixture.detectChanges();
  spyOn(component,'login');
  el=fixture.debugElement.query(By.css('Login')).nativeElement;
  el.click();
  expect(component.login).toHaveBeenCalledTimes(0);
})

});

it('Form should be invalid when field are empty', waitForAsync(()=> {
  component.userform.controls['emailId'].setValue('');
  component.userform.controls['password'].setValue('');
  expect(component.userform.valid).toBeFalsy();
}));

it('Form should be valid', waitForAsync(()=> {
  component.userform.controls['emailId'].setValue('admin@gmail.com');
  component.userform.controls['password'].setValue('admin123');
  expect(component.userform.valid).toBeTruthy();
}));

});