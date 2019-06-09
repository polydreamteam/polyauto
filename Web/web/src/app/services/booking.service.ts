import { Injectable } from '@angular/core';
import {Observable} from 'rxjs';
import {HttpClient, HttpParams} from '@angular/common/http';
import {ConnexionService} from './connexion.service';

@Injectable({
  providedIn: 'root'
})
export class BookingService {

  constructor(
    private httpClient: HttpClient
  ) { }

  postBooking(carId: number): Observable<any> {
    const params = new HttpParams().append('token', ConnexionService.getToken() )
      .append('userId', ConnexionService.getUserId().toString())
      .append('idCar', carId.toString());
    const response = this.httpClient.post<any>('http://localhost:8080/PolyAuto/bookCar', params);
    response.subscribe(
      data => {
        alert('Réservation effectuée');
      },
      err => {
        alert('Echec de la réservation');
      }
    );
    return response;
  }

  closeBooking(bookingId: number): Observable<any> {
    const params = new HttpParams().append('token', ConnexionService.getToken() )
      .append('bookingId', bookingId.toString());
    const response = this.httpClient.post<any>('http://localhost:8080/PolyAuto/closeBooking', params);
    response.subscribe(
      data => {
        alert('Rendu enregistré');
      },
      err => {
        alert('Echec du rendu');
      }
    );
    return response;
  }
}
