import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-car-info',
  templateUrl: './car-info.component.html',
  styleUrls: ['./car-info.component.scss']
})
export class CarInfoComponent implements OnInit {

@Input() longitude : number
@Input() latitude : number

  model: string = "modèle"


  constructor() { }

  ngOnInit() {
  }

  getCar() {
  //TODO : request to get car object with lat and long
  }
}
