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
  cars: any = [];
  zoom: number = 15;
  displayResearchModal: boolean = false;

  constructor(private router: Router, private connexionService: ConnexionService, private carService: CarService) { }

  ngOnInit() {
    this.getCars(1, null);
    this.setConnected();
  }


  setConnected() {
    this.connected = this.connexionService.isConnected();
  }

  goToLogin() {
    this.router.navigateByUrl("/login")
  }


  getCars(status: number, model: number) {
    this.carService.filterCars(status, model).subscribe(
      data => {
        this.cars = data.content.cars
        this.lat = data.content.cars.map(item => item.lat)
        this.long = data.content.cars.map(item => item.lon)
      },
      err => {
        console.log(err)
      }
    )
  }

  toggleModal() {
    this.displayResearchModal = !this.displayResearchModal;
  }

}
