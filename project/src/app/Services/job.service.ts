import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class JobService {

  applyClicked = new BehaviorSubject<any>(null);
  jobMasterData: any[] = [];
  categoryVal: any;
  selectedLocation: string;
  q: number = 1;
  itemsperpage: number;
  totalItems: number;

  constructor(private httpcli: HttpClient) { }

  // getJobDataFromApi(){
  //   return this.httpcli.get('https://www.themuse.com/api/public/jobs?page=1');
  // }

  getJobDataFromApiAndShowCategories(categoryval = null, location = '',page = 1 ) {
    this.categoryVal = categoryval;
    this.selectedLocation = location;
    let queryString: string = '';
    categoryval && categoryval.forEach(element => {
      const catname = element.categoryname.replace('&', '%26')
      queryString = queryString + 'category=' + catname + '&'
      console.log(queryString);
    });
    if (location != '') {
      queryString += 'location=' + location+ '&';
    }
    return this.httpcli.get('https://www.themuse.com/api/public/jobs?' + queryString + 'page=' + page + '&api_key=f4399a61c9e7a193d1cb044f002d9bb37d7855eb4b18f67be822e8aee0bc6437')
      .pipe(map((res: any) => {
        this.jobMasterData = res.results;
        this.q = res.page;
        this.itemsperpage = res.items_per_page;
        this.totalItems = res.total;

        console.log(this.jobMasterData);
        return res;
      }));

  }

  getJobDetails(jobId) {
    return this.httpcli.get('https://www.themuse.com/api/public/jobs/' + jobId);


  }



}
