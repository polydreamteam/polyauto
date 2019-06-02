import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {faUser} from '@fortawesome/free-solid-svg-icons';



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  connected: boolean = false
  faUser = faUser
  lat: number[] = [45.779246, 45.774679 ]
  long: number[] = [4.868209, 4.876094]
  zoom: number = 15

  constructor(private router: Router) { }

  ngOnInit() {
    this.getAvailableCars();
  }


  goToLogin() {
    this.router.navigateByUrl("/login")
  }

  getAvailableCars() {
    //TODO : API call to get available cars + coordonnés GPS + remplir lats et longs
  }

  openModal() {
    //TODO: Open a modal with researches elements
  }

}
