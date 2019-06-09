import {Component, Input, OnInit} from '@angular/core';
import {Car} from '../models/car.model';
import {CarService} from '../services/car.service';
import {BookingService} from '../services/booking.service';

@Component({
  selector: 'app-car-info',
  templateUrl: './car-info.component.html',
  styleUrls: ['./car-info.component.scss']
})
export class CarInfoComponent implements OnInit {

@Input() car: Car;


  constructor(public carService: CarService, private bookingService: BookingService) { }

  ngOnInit() {
  }


  bookCar() {
    this.bookingService.postBooking(this.car.idCar);
  }

}
