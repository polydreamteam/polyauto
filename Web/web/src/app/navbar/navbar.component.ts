import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import { Location } from '@angular/common';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  isConnected: boolean = true;
  profilClass: string = "menu";
  rechercheClass: string = "menu";

  constructor(private router: Router, private location:Location) { }

  ngOnInit() {
    this.setConnected();
    this.getCurrentPage();
    console.log(this.isConnected)
  }

  setConnected() {
    //TODO : Getconnected
  }

  getCurrentPage() {

    if(this.location.path().includes("login")){
        this.isConnected = false;
    }
    else if(this.location.path().includes("profil")){
      this.profilClass = "menu current";
    }
    else {
      this.rechercheClass = "menu current";
    }
  }

}
