import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';


@Injectable({
  providedIn: 'root'
})
export class ConnexionService {

  constructor(
    private httpClient: HttpClient
  ) {
  }

  static getToken() {
    return localStorage.getItem('token');
  }

  static getUserId() {
    return localStorage.getItem('id');
  }

  isConnected() {
    return localStorage.getItem('token') != null;
  }

  getConnexion(login: string, password: string): Observable<any> {
    const params = new HttpParams().append('login', login).append('password', password);
    return this.httpClient.post<any>('http://localhost:8080/PolyAuto/login', params);
  }



}
