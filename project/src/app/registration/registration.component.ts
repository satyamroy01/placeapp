import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../Services/authentication.service';
import { RoutingService } from '../Services/routing.service';
@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.css']
})
export class RegistrationComponent implements OnInit {

  registerform : FormGroup;
  firstname : FormControl;
  lastname  : FormControl;
  contact : FormControl;
  password : FormControl;
  mail : FormControl;
  errmessage : string;
  constructor(private routeobj: RoutingService,private authsvc: AuthenticationService) { 
    this.registerform = new FormGroup({
      firstName : new FormControl('',Validators.required),
      lastName : new FormControl('',Validators.required),
      emailId : new FormControl('',Validators.required ),
      //userId : new FormControl('',Validators.required),
      contact : new FormControl('',[Validators.required, Validators.minLength(10)]),
      password : new FormControl('',[Validators.required, Validators.minLength(6)])

    });
  }

  ngOnInit(): void {
  }
  showLogin(){
    this.routeobj.openLogin();
  }

 
  register(post){
    if(this.registerform.valid){
      // console.log(this.jobseeker.get('mail').value)
      // let val : string = this.jobseeker.get('mail').value;
      // let check : string = "@stackroute.in";

      // if(val.includes(check)){
      //   sessionStorage.setItem("loggedin","valid");
      // }
      // else{
      //   sessionStorage.setItem("loggedin","invalid"); 
      // }
      this.authsvc.register(post).subscribe((data)=>{
        console.log(data);

      })
      this.routeobj.openLogin();

    }
    else{
      this.errmessage="Username / password validation fails";

    }
  }

}

