import { ComponentFixture, TestBed } from '@angular/core/testing';
import { HttpClientModule } from '@angular/common/http';
import { FavJobComponent } from './fav-job.component';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule } from '@angular/forms';
fdescribe('FavJobComponent', () => {
  let component: FavJobComponent;
  let fixture: ComponentFixture<FavJobComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FormsModule, HttpClientModule, RouterTestingModule,ReactiveFormsModule],
      declarations: [ FavJobComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FavJobComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();

    fixture.whenStable().then(() => {
    fixture.detectChanges();
  let tableRows = fixture.nativeElement.querySelectorAll('tr');
  expect(tableRows.length).toBe(6);

  // Header row
  let headerRow = tableRows[0];
  expect(headerRow.cells[0].innerHTML).toBe('Company');
  expect(headerRow.cells[1].innerHTML).toBe('favJobName');
  expect(headerRow.cells[2].innerHTML).toBe('Location');
  expect(headerRow.cells[3].innerHTML).toBe('PostLevel');
  expect(headerRow.cells[4].innerHTML).toBe('Trash');

  // Data rows
  let row1 = tableRows[1];
  expect(row1.cells[0].innerHTML).toBe('Amazon');
  expect(row1.cells[1].innerHTML).toBe('Software developer');
  expect(row1.cells[2].innerHTML).toBe('Bangalore');
  expect(row1.cells[3].innerHTML).toBe('Senior');
  expect(row1.cells[4].innerHTML).toBe('delete');

  // Test more rows here..

  // done();
});
});

  it('should create', () => {
    expect(component).toBeTruthy();
  });

  it('should have getUserFavJobs function',()=>{
    expect(component.getUserFavJobs).toBeTruthy();
  });

 

  it('should have refresh function',()=>{
    expect(component.refresh).toBeTruthy();
  });

  it('should have deleteFav function',()=>{
    expect(component.deleteFav).toBeTruthy();
  });

});
