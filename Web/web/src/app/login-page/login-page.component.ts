import { Component, OnInit } from '@angular/core';
import { faEye} from '@fortawesome/free-solid-svg-icons';

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

  constructor() {
    this.togglePassword = false;

  }

  ngOnInit() {
  }

  changeInput(input: any): any {
    input.type = input.type === 'password' ? 'text' : 'password';
  }

  submit() {
  //TODO : send userName and password to login
  }


}
