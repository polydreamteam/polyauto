import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {ConnexionService} from './connexion.service';


@Injectable({
  providedIn: 'root'
})
export class CarService {
  constructor(
    private httpClient: HttpClient
  ) {
  }
  getAvailableCar(): Observable<any> {
    const params = new HttpParams().set('token', ConnexionService.getToken() );
    return this.httpClient.get<any>('http://localhost:8080/PolyAuto/getAvailableCars', {params});
  }

  getNotAvailableCar(): Observable<any> {
    const params = new HttpParams().set('token', ConnexionService.getToken() );
    return this.httpClient.get<any>('http://localhost:8080/PolyAuto/getNotAvailableCars', {params});
  }

  getWithStatusAndModel(status: string, model: string): Observable<any> {
    const params = new HttpParams().set('token', ConnexionService.getToken() )
      .append('status', status.toString())
      .append('model', (model || {}).toString());
    return this.httpClient.get<any>('http://localhost:8080/PolyAuto/getWithStatusAndModel', {params});
  }

  modelToString(model: number) {
    switch (model) {
      case 1: return 'Renault twingo';
      case 2: return 'Porsche cayenne';
      case 3: return 'Fiat Multipla';
    }
  }

  getModels() {
    return [1, 2, 3];
  }

  filterCars(status: string, model: string) {
    if (model) {
        return this.getWithStatusAndModel(status, model);
    } else {
      if (status === '0') {
        return this.getNotAvailableCar();
      } else {
        return this.getAvailableCar();
      }
    }
  }


}
