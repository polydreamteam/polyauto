import {Component, Input, OnInit} from '@angular/core';
import {Car} from "../models/car.model";

@Component({
  selector: 'app-car-info',
  templateUrl: './car-info.component.html',
  styleUrls: ['./car-info.component.scss']
})
export class CarInfoComponent implements OnInit {

@Input() car : Car;


  constructor() { }

  ngOnInit() {
    console.log(this.car)
  }

  getCar() {
  //TODO : request to get car object with lat and long
  }
}
