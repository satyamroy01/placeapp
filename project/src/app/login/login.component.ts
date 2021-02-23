import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthenticationService } from '../Services/authentication.service';
import{RoutingService}from '../Services/routing.service'

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {
  userform : FormGroup;
  errmessage : string;


  constructor(private routeobj: RoutingService,private authsvc: AuthenticationService) { 
    this.userform = new FormGroup({
      emailId : new FormControl('',[Validators.required,Validators.email]),
      password : new FormControl('',[Validators.required, Validators.minLength(6)])

    });
  }

  ngOnInit(): void {
  }
  showRegister(){
    this.routeobj.openRegister();
  }
  login(post){
    if(this.userform.valid){

      this.authsvc.login(post).subscribe((data)=>{
        sessionStorage.setItem('token',data.token);
        sessionStorage.setItem('userId',this.userform.get('emailId').value);
        console.log(sessionStorage.getItem('token'));
        this.routeobj.openHome();
      }
        
      )

      // console.log(this.userform.get('username').value)
      // let val : string = this.userform.get('username').value;
      // let check : string = "@stackroute.in";

      // if(val.includes(check)){
      //   sessionStorage.setItem("loggedin","valid");
      // }
      // else{
      //   sessionStorage.setItem("loggedin","invalid"); 
      // }
      // this.routeobj.openDashboard();
      

    }
    else{
      this.errmessage="Username / password validation fails";

    }

    
  }

}
