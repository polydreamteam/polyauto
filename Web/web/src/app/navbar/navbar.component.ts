import { Component, OnInit } from '@angular/core';
import {Router} from '@angular/router';
import { Location } from '@angular/common';
import {faPowerOff} from '@fortawesome/free-solid-svg-icons';
import {faSearch} from '@fortawesome/free-solid-svg-icons';
import {faUser} from '@fortawesome/free-solid-svg-icons';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent implements OnInit {

  faPowerOff = faPowerOff;
  faSearch = faSearch;
  faUser = faUser;

  constructor(private router: Router, private location: Location) { }

  ngOnInit() {}



  logout() {
    localStorage.clear();
  }

  getCurrentPage(path: string) {
    if (this.location.path().includes(path)) {
      return 'menu current';
    } else {
      return 'menu';
    }
  }

}
