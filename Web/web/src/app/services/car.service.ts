import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";
import {ConnexionService} from "./connexion.service";
import {Car} from "../models/car.model";


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

  getNotAvailableCar(): Observable<any> {
    let params = new HttpParams().set('token', ConnexionService.getToken() );
    return this.httpClient.get<any>('http://localhost:8080/getNotAvailableCars', {params : params});
  }

  getWithStatusAndModel(status: number, model:number) : Observable<any> {
    let params = new HttpParams().set('token', ConnexionService.getToken() ).append('status', status.toString()).append('model', model.toString())
    return this.httpClient.get<any>('http://localhost:8080/getWithStatusAndModel', {params : params});
  }

  modelToString(model: number) {
    switch(model) {
      case 1: return "Renault twingo"
      case 2: return "Porsche cayenne"
      case 3: return "Fiat Multipla"
    }
  }

  getModels() {
    return [1,2,3]
  }

  filterCars(status: number, model: number) {
    if(model) {
        return this.getWithStatusAndModel(status, model)
    }
    else {
      if(status == 0) {
        return this.getNotAvailableCar()
1      }
      else {
        return this.getAvailableCar()
      }
    }
  }



}
