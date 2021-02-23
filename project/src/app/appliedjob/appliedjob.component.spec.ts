import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AppliedjobComponent } from './appliedjob.component';
import { HttpClientModule } from '@angular/common/http';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
fdescribe('AppliedjobComponent', () => {
  let component: AppliedjobComponent;
  let fixture: ComponentFixture<AppliedjobComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientModule, RouterTestingModule,ReactiveFormsModule],
      declarations: [ AppliedjobComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AppliedjobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have getUserAppliedJobs function',()=>{
    expect(component.getUserAppliedJobs).toBeTruthy();
  });

  it('should have refresh function',()=>{
    expect(component.refresh).toBeTruthy();
  });

  it('should have deleteAppliedJob function',()=>{
    expect(component.deleteAppliedJob).toBeTruthy();
  });
});
