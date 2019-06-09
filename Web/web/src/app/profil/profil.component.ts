import { Component, OnInit } from '@angular/core';
import {faStar as solidStar} from '@fortawesome/free-solid-svg-icons';
import {faPen} from '@fortawesome/free-solid-svg-icons';
import {faStar} from '@fortawesome/free-regular-svg-icons';
import {ProfilService} from "../services/profil.service";
import {Profil} from "../models/profil";
import {Resa} from "../services/resa";


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  faSolidStar = solidStar
  faRegularStar = faStar
  faPen = faPen
  profil: Profil
  resas: Resa[]


  constructor(private profilService: ProfilService) { }

  ngOnInit() {
    this.getProfile()
  }

  getProfile() {
    this.profilService.getProfil().subscribe(
      data => {
        this.profil = data.content.user
        this.resas = data.content.bookings
        console.log(this.resas)
      },
      err => {
        console.log(err)
      })
  }


  editProfile() {
    //TODO: edit profil function
  }

  editResa(event) {
    //TODO: edit resa
  }
}
