import {OnInit, Component, ChangeDetectorRef} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';

import{FavJob} from '../fav-job';
import{FavJobService} from '../Services/fav-job.service';

@Component({
  selector: 'app-fav-job',
  templateUrl: './fav-job.component.html',
  styleUrls: ['./fav-job.component.css']
})
export class FavJobComponent implements OnInit {

  myFavJobArray: Array<any>;
  p : any;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns: string[] = ['Company','favJobName','Location', 'PostLevel','Trash'];
  
  constructor (private favJobsvc : FavJobService, private changeDetector:ChangeDetectorRef ){}
  ngOnInit() {
    this.getUserFavJobs();
  }
  getUserFavJobs(){
    if(this.favJobsvc.myFavJobSvcArray){
      this.myFavJobArray = this.favJobsvc.myFavJobSvcArray;
      console.log("value success");
      console.log(this.myFavJobArray);
      
      
    }
    else{
      this.favJobsvc.getMyFavJobs(sessionStorage.getItem('userId')).subscribe(
        (response)=>{
          this.myFavJobArray = response.favJobList;
          console.log("Favjob details:");
          console.log(this.myFavJobArray);
        }
      )

    }
    
  }

  refresh(){
    this.favJobsvc.getMyFavJobs(sessionStorage.getItem('userId')).subscribe(
      (response)=>{
        this.myFavJobArray = response.favJobList;
        console.log("Favjob details:");
        console.log(this.myFavJobArray);
      }
    )

  }

 

deleteFav(jobId){
  console.log(jobId);
  this.favJobsvc.deleteMyFavJobs(sessionStorage.getItem('userId'),jobId).subscribe(
    (response)=>{
      console.log(response);
     // this.getUserFavJobs();
      // const index = this.myFavJobArray.findIndex((item)=>item.favJobId==jobId)
       //  this.myFavJobArray.splice(index,1);
       //  this.changeDetector.detectChanges();
       this.refresh();
    }
  )

 // window.location.reload();
  
}
    
}


