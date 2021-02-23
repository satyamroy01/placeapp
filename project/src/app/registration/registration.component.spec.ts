import { waitForAsync,ComponentFixture, TestBed, fakeAsync } from '@angular/core/testing';
import{ BrowserModule, By}from '@angular/platform-browser'
import { Component, OnInit } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
import { RegistrationComponent } from './registration.component';
import { HttpClientModule } from '@angular/common/http';
import { post } from 'jquery';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import {MatCardModule} from '@angular/material/card';
import {MatIconModule} from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
fdescribe('RegistrationComponent', () => {
  let component: RegistrationComponent;
  let fixture: ComponentFixture<RegistrationComponent>;
  let el: HTMLElement;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RegistrationComponent ],
      imports: [FormsModule, HttpClientModule, RouterTestingModule,MatIconModule,MatTableModule,
        ReactiveFormsModule,MatFormFieldModule,MatCardModule,MatExpansionModule,MatButtonModule,
        MatInputModule,MatToolbarModule,BrowserAnimationsModule],
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(RegistrationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('form invalid when empty', () => {
    component.registerform.controls.firstName.setValue('');
    component.registerform.controls.lastName.setValue('');
    component.registerform.controls.contact.setValue('');
    component.registerform.controls.emailId.setValue('');
    component.registerform.controls.password.setValue('');
    expect(component.registerform.valid).toBeFalsy();
  });

  it('firstName field validity', () => {
    const firstName = component.registerform.controls.firstName;
    expect(firstName.valid).toBeFalsy();

    firstName.setValue('');
    expect(firstName.hasError('required')).toBeTruthy();

  });

  it('lastName field validity', () => {
    const lastName = component.registerform.controls.lastName;
    expect(lastName.valid).toBeFalsy();

    lastName.setValue('');
    expect(lastName.hasError('required')).toBeTruthy();
  });

  it('contact field validity', () => {
    const contact = component.registerform.controls.contact;
    expect(contact.valid).toBeFalsy();

    contact.setValue('');
    expect(contact.hasError('required')).toBeTruthy();

  });

  // it('userId field validity', () => {
  //   const userId = component.registerform.controls.userId;
  //   expect(userId.valid).toBeFalsy();

  //   userId.setValue('');
  //   expect(userId.hasError('required')).toBeTruthy();

  // });

  it('email field validity', () => {
    const emailId = component.registerform.controls.emailId;
    expect(emailId.valid).toBeFalsy();

    emailId.setValue('');
    expect(emailId.hasError('required')).toBeTruthy();
  });


it('password field validity', () => {
  const password = component.registerform.controls.password;
  expect(password.valid).toBeFalsy();

  password.setValue('');
  expect(password.hasError('required')).toBeTruthy();

});

it('Should set submitted to true', waitForAsync(() => {
  component.register(post);
  expect(component.register).toBeTruthy();

}));

it('Should call the login method', () =>{ fakeAsync(() =>{
  fixture.detectChanges();
  spyOn(component,'register');
  el=fixture.debugElement.query(By.css('register')).nativeElement;
  el.click();
  expect(component.register).toHaveBeenCalledTimes(0);
})
});
it('Form should be invalid when field are empty', waitForAsync(()=> {
  component.registerform.controls['emailId'].setValue('');
  component.registerform.controls['password'].setValue('');
  expect(component.registerform.valid).toBeFalsy();
}));


});
