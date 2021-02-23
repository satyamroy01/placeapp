import {ComponentFixture, TestBed } from '@angular/core/testing';
import { By } from '@angular/platform-browser';
import { FooterComponent } from './footer.component';

fdescribe('FooterComponent', () => {
  let component: FooterComponent;
  let fixture: ComponentFixture<FooterComponent>;

  beforeEach(async() => {
    TestBed.configureTestingModule({
      declarations: [ FooterComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FooterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });  

  it('mainfooter should exist',()=>{
    let footerobj=fixture.debugElement.query(By.css(".mainfooter"));
    expect(footerobj).toBeTruthy();
});

it('Career should exist',()=>{
  let footerpadobj=fixture.debugElement.query(By.css(".footer-pad"));
  expect(footerpadobj).toBeTruthy();
});

it('Policies should exist',()=>{
  let footerpadobj=fixture.debugElement.query(By.css(".footer-pad"));
  expect(footerpadobj).toBeTruthy();
});
it('Terms & Conditions should exist',()=>{
  let footerpadobj=fixture.debugElement.query(By.css(".footer-pad"));
  expect(footerpadobj).toBeTruthy();
});

it('"Follow Us" should exist',()=>{
  let colobj=fixture.debugElement.query(By.css(".col-md-3"));
  expect(colobj).toBeTruthy();
});

});

