import { Component, OnInit } from '@angular/core';
import{ApplyJobService} from '../Services/apply-job.service'


@Component({
  selector: 'app-appliedjob',
  templateUrl: './appliedjob.component.html',
  styleUrls: ['./appliedjob.component.css']
})
export class AppliedjobComponent implements OnInit {

  myAppliedjobArray: Array<any>;
  p : any;
  /** Columns displayed in the table. Columns IDs can be added, removed, or reordered. */
  displayedColumns: string[] = ['Company','appliedJobName','Location', 'PostLevel','Remove'];
  
  constructor (private Jobsvc : ApplyJobService ){}
  ngOnInit() {
    
    this.getUserAppliedJobs();
   
  }
  
  getUserAppliedJobs(){
    if(this.Jobsvc.myAppliedJobSvcArray){
      this.myAppliedjobArray = this.Jobsvc.myAppliedJobSvcArray
    }
    else{
      this.Jobsvc.getMyAppliedJobs(sessionStorage.getItem('userId')).subscribe(
        (response)=>{
          this.myAppliedjobArray = response.appliedJobsList;
          console.log("Applied Job details:");
          console.log(this.myAppliedjobArray);
        }
      )

    }
    
  }

  refresh(){
    this.Jobsvc.getMyAppliedJobs(sessionStorage.getItem('userId')).subscribe(
      (response)=>{
        this.myAppliedjobArray = response.appliedJobsList;
        console.log("Applied Job details:");
        console.log(this.myAppliedjobArray);
      }
    )

  }

  deleteAppliedJob(jobId){
    this.Jobsvc.deleteMyAppliedJobs(sessionStorage.getItem('userId'),jobId).subscribe(
      (response)=>{
        console.log(response);
        //this.getUserAppliedJobs();
          //const index = this.myAppliedjobArray.findIndex((item)=>item.appliedJobsId==jobId)
          //this.myAppliedjobArray.splice(index,1);
          this.refresh();
      }
    )
    //window.location.reload();
  }
    
}

