import { Component, OnInit } from '@angular/core';
import { Subscription } from 'rxjs';
import { appliedjob } from '../Model/appliedJobModel';
import { FavJobDetails } from '../Model/favJobDetailsModel';
import { Favjob } from '../Model/favJobModel';
import { appliedjobcount } from '../Model/jobCountModel';
import { job5 } from '../Model/jobmodel';
import { ApplyJobService } from '../Services/apply-job.service';
import { FavJobService } from '../Services/fav-job.service';
import { JobCountService } from '../Services/job-count.service';
import { JobService } from '../Services/job.service';
import { RoutingService } from '../Services/routing.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-search-results',
  templateUrl: './search-results.component.html',
  styleUrls: ['./search-results.component.css']
})
export class SearchResultsComponent implements OnInit {

  jobData:Array<any>;
  q: number = 1;
  itemsperpage: number;
  totalItems: number;
  location: string;
  categoryval:any;
  isLoggedIn = false;

  myJobArray: Array<any>;
  myFavJobArray: Array<any>;
  jobCountArray: appliedjobcount[] = [];
  itemsCount: appliedjobcount;
  jobCountObj = {};
  jobCountData: appliedjobcount = {};
  jobcategories: Array<any>;
  trendingJob: Array<any>;
  trendingJobResult: any;

  applySubscription: Subscription;
  closeDialogSubscription: Subscription;

  constructor(private jobsvc: JobService,private applyjobsvc: ApplyJobService,
    private favJobsvc: FavJobService,
    private jobCount: JobCountService,private routersvc: RoutingService,
    private snack : MatSnackBar) { }

  ngOnInit(): void {
    console.log(this.jobsvc.jobMasterData);
    this.jobData = this.jobsvc.jobMasterData;
    this.q = this.jobsvc.q;
    this.itemsperpage = this.jobsvc.itemsperpage;
    this.totalItems = this.jobsvc.totalItems;
    this.categoryval=this.jobsvc.categoryVal;
    this.location = this.jobsvc.selectedLocation;

    this.loggedInCheck();
    this.getUserAppliedJobs();
    this.getUserFavJobs();
    this.subscribeModalClose();

  }

  getJobData(){
    this.jobsvc.getJobDataFromApiAndShowCategories(this.categoryval,this.location,this.q).subscribe(
      (response)=>{
        console.log("page changed" + this.q);
        
        console.log(response);
        this.jobData = response["results"];

      }
    )
  }
  loggedInCheck() {
    if (sessionStorage.getItem('token') !== null) {
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }
   // to retrieve loggedin user's applied jobs
   getUserAppliedJobs() {
    this.applyjobsvc.getMyAppliedJobs(sessionStorage.getItem('userId')).subscribe(
      (response) => {
        this.applyjobsvc.myAppliedJobSvcArray = response.appliedJobsList;
        this.myJobArray = this.applyjobsvc.myAppliedJobSvcArray
        //console.log(this.myJobArray);

      }
    )
  }
  // to retrieve loggedin user's favourite jobs
  getUserFavJobs() {
    this.favJobsvc.getMyFavJobs(sessionStorage.getItem('userId')).subscribe(
      (response) => {
        
        this.favJobsvc.myFavJobSvcArray = response.favJobList;
        this.myFavJobArray=this.favJobsvc.myFavJobSvcArray;
        console.log(this.favJobsvc.myFavJobSvcArray);


      }
    )
  }

  // to check whether the particular job is applied or not. 
  // some will return boolean on the first true case
  isJobApplied(jobId: number) {
    if (!this.myJobArray || !this.myJobArray.length) {

      return false;
    }

    return this.myJobArray.some((item) => (item.appliedJobsId == jobId));

  }

  isJobMarkedAsFav(jobId: number) {
    return this.favJobsvc.myFavJobSvcArray.some((item) => (item.favJobId == jobId));

  }

  addFav(jobdata) {
    let favjobfile: FavJobDetails = {};
    let favjobfileArray: FavJobDetails[] = [];
    let favJobDetails: Favjob = {};


    console.log(jobdata);
    favjobfile.favJobId = jobdata.id;
    favjobfile.favJobCompany = jobdata.company.name;
    favjobfile.favJobLocation = jobdata.locations[0].name;
    favjobfile.favJobName = jobdata.name;
    favjobfile.favJobDesc = "testDesc";
    favjobfile.favJobPostLevel = jobdata.levels[0].name;
    favJobDetails.userId = sessionStorage.getItem('userId');
    console.log(favJobDetails.userId);
    favjobfileArray.push(favjobfile);
    favJobDetails.favJobList = favjobfileArray;

    console.log("favourite job data sent");
    console.log(favJobDetails);

    this.favJobsvc.addFavJob(favJobDetails).subscribe(
      (response) => {
        console.log("favourite job response");
        console.log(response);
        //this.getJobDataInDashboard();
        this.getUserFavJobs();

      }
    )
    //window.location.reload();
  }
  removeFav(jobdata) {
    this.favJobsvc.deleteMyFavJobs(sessionStorage.getItem('userId'), jobdata.id).subscribe(
      (response) => {
        console.log(response);
        //this.getJobDataInDashboard();
        //this.getUserFavJobs();
        const index = this.favJobsvc.myFavJobSvcArray.findIndex((item)=>item.favJobId==jobdata.id)
        this.favJobsvc.myFavJobSvcArray.splice(index,1);
      }
    )
  }

  applyJob(jobdata) {
    let jobfile: job5 = {};
    let jobtest: job5[] = [];
    let appliedJobDetails: appliedjob = {};


    console.log(jobdata);
    jobfile.appliedJobsId = jobdata.id;
    jobfile.appliedJobsCompany = jobdata.company.name;
    jobfile.appliedJobsLocation = jobdata.locations[0].name;
    jobfile.appliedJobsName = jobdata.name;
    jobfile.appliedJobsStatus = "pending";
    jobfile.appliedJobsDesc = "testDesc";
    jobfile.appliedJobsPostLevel = jobdata.levels[0].name;
    jobfile.createdAt = "2021-01-29T12:13:09.355Z";
    appliedJobDetails.userId = sessionStorage.getItem('userId');
    console.log(appliedJobDetails.userId);
    jobtest.push(jobfile);
    appliedJobDetails.appliedJobsList = jobtest;

    console.log("applied job data sent");
    console.log(appliedJobDetails);


    this.jobCountData.jobId = jobdata.id;
    console.log("count data sent");
    console.log(this.jobCountData);

    this.applyjobsvc.applyJob(appliedJobDetails).subscribe(
      (response: any) => {
        console.log("applied job response");
        console.log(response);
        //this.getJobDataInDashboard();
        this.getUserAppliedJobs();
      }
    )
    this.jobCount.applyJobCounter(this.jobCountData).subscribe(
      (response: any) => {
        console.log("count response");
        console.log(response);
      }

    )

  }

  showPopupGuest() {
    // alert("SignUp to explore 100+ features of PlaceApp!")
    this.snack.open("SignUp to explore 100+ features of PlaceApp!",'Ok',
        {
          duration:2000,
          horizontalPosition: 'center',
          verticalPosition: 'bottom',
          panelClass: ["snack-bar-fav"]
        }) ;
  }

   // to open popup to show job info
   showJobDetails(jobId) {
    this.routersvc.openJob(jobId);
  }

  // subscribeApplyBtn() {
  //   this.applySubscription = this.jobservice.applyClicked.subscribe(jobDetail => {
  //     if (jobDetail) {
  //       if (this.isJobApplied(jobDetail.id)) {
  //         alert('You have already applied for this job.')
  //       } else {
  //         this.applyJob(jobDetail)
  //       }

  //     }

  //   })

  // }

  subscribeModalClose() {
    this.closeDialogSubscription = this.routersvc.modalClose.subscribe(res => {
      if (res) {
        if (res == 'favModal') {
          //this.getJobDataInDashboard();
          this.getUserFavJobs();

        }
        else if (res == 'appliedModal') {
          //this.getJobDataInDashboard();
          this.getUserAppliedJobs();

        }

      }
    }

    )

  }

}
