import {HttpClient, HttpParams} from '@angular/common/http';
import {Observable} from 'rxjs';
import {Injectable} from '@angular/core';
import {ConnexionService} from './connexion.service';
import {Profil} from '../models/profil';


@Injectable({
  providedIn: 'root'
})
export class ProfilService {
  constructor(
    private httpClient: HttpClient
  ) {  }

  getProfil(): Observable<any> {
    const params = new HttpParams().set('token', ConnexionService.getToken() );
    return this.httpClient.get<any>('http://localhost:8080/PolyAuto/getProfileComplete', {params});
  }

  updateProfil(profil: Profil): Observable<any> {
    const params = new HttpParams().append('token', ConnexionService.getToken() )
      .append('login', profil.login)
      .append('password', profil.password)
      .append('firstname', profil.firstname)
      .append('lastname', profil.lastname)
      .append('note', profil.note.toString());
    const response = this.httpClient.post<any>('http://localhost:8080/PolyAuto/modifyProfileInfo', params);
    response.subscribe(
      data => {
        alert('Modification effectuée');
      },
      err => {
        alert('Echec de la modification');
      }
    );
    return response;
  }
}
