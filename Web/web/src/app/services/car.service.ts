import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {ConnexionService} from "./connexion.service";


@Injectable({
  providedIn: 'root'
})
export class CarService {
  constructor(
    private httpClient:HttpClient
  ) {
  }
  getAvailableCar() : Observable<any>
  {
    let params = new HttpParams().set('token', ConnexionService.getToken() );
    return this.httpClient.get<any>('http://localhost:8080/getAvailableCars', {params : params});
  }

  isConnected() {
    return localStorage.getItem('token') != null;
  }


}
