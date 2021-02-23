import { Component, OnInit, TemplateRef } from '@angular/core';
import { BsModalService, BsModalRef } from 'ngx-bootstrap/modal';
import { RoutingService } from '../Services/routing.service';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
// Class to display header
export class HeaderComponent implements OnInit {
  isLoggedIn = false;
  modalRef: BsModalRef;
  constructor(private modalService: BsModalService, private routesvc: RoutingService){
    

  }


  ngOnInit() {
    this.loggedInCheck();
  }
  // Function to clear all sessionStorage  items and reload to landing page
  signOut() {
    sessionStorage.clear();
    location.reload();
  }

  loggedInCheck() {
    if (sessionStorage.getItem('token') !== null) {
      this.isLoggedIn = true;
    } else {
      this.isLoggedIn = false;
    }
  }

  openModal(template: TemplateRef<any>) {
    this.modalRef = this.modalService.show(template,{ignoreBackdropClick:true,class: 'modal-lg'});
  }

  hideFavModal(){
    
    this.routesvc.modalClose.next('favModal');
    this.modalRef.hide();
  }
  hideAppliedModal(){
    
    this.routesvc.modalClose.next('appliedModal');
    this.modalRef.hide();
  }

  
}
