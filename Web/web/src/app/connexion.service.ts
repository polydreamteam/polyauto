import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from "rxjs";
import {Injectable} from "@angular/core";


@Injectable({
  providedIn: 'root'
})
export class ConnexionService {
  constructor(
    private httpClient:HttpClient
  ) {
  }
  getConnexion(login : string, password: string) : Observable<any>
  {
    let params = new HttpParams().append('login', login).append('password', password);
    return this.httpClient.post<any>('http://localhost:8080/login', params);
  }


}
