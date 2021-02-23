import { Component, OnDestroy, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { JobService } from '../Services/job.service';
//import { appliedjob } from '../Model/jobmodel';
import { ApplyJobService } from '../Services/apply-job.service';
import { job5 } from '../Model/jobmodel';
import { appliedjob } from '../Model/appliedJobModel';
import { JobCountService } from '../Services/job-count.service';
import { appliedjobcount } from '../Model/jobCountModel';
import { RoutingService } from '../Services/routing.service';
import { Subscription } from 'rxjs';
import { FavJobDetails } from '../Model/favJobDetailsModel';
import { Favjob } from '../Model/favJobModel';
import { FavJobService } from '../Services/fav-job.service';
import { MatSnackBar } from '@angular/material/snack-bar';


@Component({
  selector: 'app-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit, OnDestroy {
  q: number = 1;
  itemsperpage: number;
  totalItems: number;
  jobdata: Array<any>;
  myJobArray: Array<any>;
  myFavJobArray: Array<any>;
  jobCountArray: appliedjobcount[] = [];
  itemsCount: appliedjobcount;
  jobCountObj = {};
  jobCountData: appliedjobcount = {};
  jobSearchForm: FormGroup;
  jobcategories: Array<any>;
  isLoggedIn = false;
  trendingJob: Array<any>;
  trendingJobResult: any;

  applySubscription: Subscription;
  closeDialogSubscription: Subscription;


  constructor(private jobservice: JobService,
    private applyjobsvc: ApplyJobService,
    private favJobsvc: FavJobService,
    private jobCount: JobCountService,
    private routersvc: RoutingService,
    private snack : MatSnackBar
  ) {
    this.jobSearchForm = new FormGroup({
      searchval: new FormControl('', Validators.required)

    });
    this.jobcategories = [

      {
        'categoryname': 'Account Management',
        'selected': false
      },
      {
        'categoryname': 'Creative & Design',
        'selected': false
      },
      {
        'categoryname': 'Data Science',
        'selected': false
      },
      {
        'categoryname': 'Education',
        'selected': false
      },
      {
        'categoryname': 'Finance',
        'selected': false
      },
      {
        'categoryname': 'Legal',
        'selected': false
      },
      {
        'categoryname': 'Operations',
        'selected': false
      },
      {
        'categoryname': 'Retail',
        'selected': false
      },
      {
        'categoryname': 'Social Media & Community',
        'selected': false
      },
      {
        'categoryname': 'Business & Strategy',
        'selected': false
      },
      {
        'categoryname': 'Customer Service',
        'selected': false
      },
      {
        'categoryname': 'Editorial',
        'selected': false
      },
      {
        'categoryname': 'Engineering',
        'selected': false
      },
      {
        'categoryname': 'Fundraising & Development',
        'selected': false
      },
      {
        'categoryname': 'HR & Recruiting',
        'selected': false
      },
      {
        'categoryname': 'Marketing & PR',
        'selected': false
      },
      {
        'categoryname': 'Project & Product Management',
        'selected': false
      },
      {
        'categoryname': 'Sales',
        'selected': false
      }
    ];
    
  }

  ngOnInit(): void {
    this.getJobDataInDashboard();
    this.loggedInCheck();
    if(this.isLoggedIn){
      this.getUserAppliedJobs();
    this.getUserFavJobs();
    this.subscribeApplyBtn();
    this.subscribeModalClose();

    }
    

  }
  // since we are subscribing to a behaviour subject we need to unsubscribe the session when navigating to
  // other components from dashboard. or Memory leak will occur. 
  ngOnDestroy(): void {
    if(this.isLoggedIn){
      this.applySubscription.unsubscribe();
    this.closeDialogSubscription.unsubscribe();

    }
    
  }

  loggedInCheck() {
    if (sessionStorage.getItem('token') !== null) {
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }

  // to open popup to show job info
  showJobDetails(jobId) {
    this.routersvc.openJob(jobId);
  }

  // to retrieve loggedin user's applied jobs
  getUserAppliedJobs() {
    this.applyjobsvc.getMyAppliedJobs(sessionStorage.getItem('userId')).subscribe(
      (response) => {
        this.applyjobsvc.myAppliedJobSvcArray = response.appliedJobsList;
        this.myJobArray = this.applyjobsvc.myAppliedJobSvcArray
        console.log(this.myJobArray);

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

  getJobDataInDashboard() {
    let selectedCategories = this.jobcategories.filter((data) =>
      data.selected
    )
    if (selectedCategories.length < 1) {
      selectedCategories = null;
    }
    this.jobservice.getJobDataFromApiAndShowCategories(selectedCategories,'' ,this.q).subscribe(
      (response: any) => {
        this.jobdata = response["results"];
        this.q = response.page;
        this.itemsperpage = response.items_per_page;
        this.totalItems = response.total;
        console.log(this.jobdata);
        this.getJobCounters();

        // this.appliedJobDetails.appliedJobsId = this.jobdata.id;
        // console.log(this.appliedJobDetails);

      }
    )
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
        this.getJobCounters();
      }
    )
    this.jobCount.applyJobCounter(this.jobCountData).subscribe(
      (response: any) => {
        console.log("count response");
        console.log(response);
      }

    )

    // window.location.reload();

  }
  // to get the global job count details from Analytics svc
  getJobCounters() {
    this.jobCount.getAppliedJobsCounter().subscribe(
      (response) => {
        // console.log("analytics");
        this.jobCountArray = response;
        this.itemsCount = response;
        // console.log("count array is");
        //console.log(this.itemsCount);
        this.jobCountArray.forEach(item => {
          this.jobCountObj[item.jobId] = item.counter;

        });
        //console.log(this.jobCountObj);
        this.findTrendingJob();
        this.assignCountToJobs();

      }
    )
  }

  assignCountToJobs() {
    this.jobdata.forEach((item) => {
      item.count = this.jobCountObj[item.id] ? this.jobCountObj[item.id] : 0;

    });

  }
  selectCategory(index) {
    this.jobcategories[index].selected = !this.jobcategories[index].selected;
  }

  showDetails() {
    this.routersvc.openJob(0);
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

  findTrendingJob() {
    this.trendingJob = this.jobCountArray.sort((a, b) => (a.counter < b.counter) ? 1 : -1);
    console.log(this.trendingJob[0].jobId);
    this.jobservice.getJobDetails(this.trendingJob[0].jobId).subscribe(
      (response) => {
        console.log(response);
        this.trendingJobResult = response;

      }
    )


  }

  subscribeApplyBtn() {
    this.applySubscription = this.jobservice.applyClicked.subscribe(jobDetail => {
      if (jobDetail) {
        if (this.isJobApplied(jobDetail.id)) {
          alert('You have already applied for this job.')
        } else {
          this.applyJob(jobDetail)
        }

      }

    })

  }

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
