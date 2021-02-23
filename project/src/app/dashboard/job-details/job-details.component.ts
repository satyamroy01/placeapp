import { Component, Inject, OnInit, ViewChild } from '@angular/core';
import { MatDialog, MatDialogRef, MAT_DIALOG_DATA } from '@angular/material/dialog';
import { ActivatedRoute } from '@angular/router';
import { JobService } from 'src/app/Services/job.service';
import { RoutingService } from 'src/app/Services/routing.service';
import {Location} from '@angular/common';

@Component({
  selector: 'app-job-details',
  template: '',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsComponent implements OnInit {

  constructor(public dialog: MatDialog, public activatedRoute: ActivatedRoute) {
    const jobId = this.activatedRoute.snapshot.params.jobId;
    this.dialog.open(JobDetailsViewerComponent, {
      data: jobId
    });
  }

  ngOnInit(): void {
  }

}

@Component({
  selector: 'app-job-details-viewer',
  templateUrl: './job-details.component.html',
  styleUrls: ['./job-details.component.css']
})
export class JobDetailsViewerComponent implements OnInit {
  jobId: number;
  jobDetails: any;
  isLoggedIn = false;
  constructor(private dialog: MatDialogRef<JobDetailsViewerComponent>,
    @Inject(MAT_DIALOG_DATA) private data: any, private jobsvc: JobService, private routeSvc:RoutingService,private _location: Location) {
    this.jobId = data;
    dialog.disableClose=true;
  }

  ngOnInit(): void {
    this.getJobData();
    this.loggedInCheck();
  }

  getJobData() {
    this.jobsvc.getJobDetails(this.jobId).subscribe(
      (response: any) => {
        this.jobDetails = response;
        document.getElementById('jobDetailsContent').innerHTML = response.contents;
        console.log(response);
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
  apply(){
    this.jobsvc.applyClicked.next(this.jobDetails);
    this._location.back();
  }
  closeDialog(){
    this._location.back();
  }

}
