import { Component, OnInit } from '@angular/core';
import { faEye} from '@fortawesome/free-solid-svg-icons';
import {ConnexionService} from '../services/connexion.service';
import {Router} from '@angular/router';

@Component({
  selector: 'app-login-page',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.scss']
})
export class LoginPageComponent implements OnInit {

  togglePassword: boolean;
  faEye = faEye;
  userName: string;
  password: string;

  constructor(private connexionService: ConnexionService, private router: Router) {
    this.togglePassword = false;

  }

  ngOnInit() {
  }

  changeInput(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  submit() {
    this.connexionService.getConnexion(this.userName, this.password).subscribe(
      (response) => {
        localStorage.setItem('token', response.content.token);
        localStorage.setItem('id', response.content.userId);
        this.router.navigateByUrl('/home');
      }
    );
  }


}
