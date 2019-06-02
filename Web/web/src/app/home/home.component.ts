import { Component, OnInit } from '@angular/core';
import { Router } from "@angular/router";
import {faUser} from '@fortawesome/free-solid-svg-icons';
import {ConnexionService} from "../services/connexion.service";
import {CarService} from "../services/car.service";
import {Car} from "../models/car.model";



@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.scss']
})
export class HomeComponent implements OnInit {

  connected: boolean = false;
  faUser = faUser;
  lat: number[] = [];
  long: number[] = [];
  availableCars: Car[] = [];
  zoom: number = 15;

  constructor(private router: Router, private connexionService: ConnexionService, private carService: CarService) { }

  ngOnInit() {
    this.getAvailableCars();
    this.setConnected();
  }


  setConnected() {
    this.connected = this.connexionService.isConnected();
  }

  goToLogin() {
    this.router.navigateByUrl("/login")
  }

  getAvailableCars() {
    this.carService.getAvailableCar().subscribe(
      resp => {
        this.lat = resp.content.avalaibleCars.map(item => item.lat);
        this.long = resp.content.avalaibleCars.map(item => item.lon);
        this.availableCars = resp.content.avalaibleCars;
      },
      err => {
        console.log(err)
      }
    )

  }

  openModal() {
    //TODO: Open a modal with researches elements
  }

}
