import { Component, OnInit } from '@angular/core';
import {faStar as solidStar} from '@fortawesome/free-solid-svg-icons';
import {faPen} from '@fortawesome/free-solid-svg-icons';
import {faStar} from '@fortawesome/free-regular-svg-icons';
import {faCheck} from '@fortawesome/free-solid-svg-icons';
import {ProfilService} from '../services/profil.service';
import {Profil} from '../models/profil';
import {Resa} from '../models/resa';
import {CarService} from '../services/car.service';
import {BookingService} from '../services/booking.service';
import {ConnexionService} from '../services/connexion.service';
import {Router} from '@angular/router';


@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss']
})
export class ProfilComponent implements OnInit {

  faSolidStar = solidStar;
  faRegularStar = faStar;
  faPen = faPen;
  faCheck = faCheck;
  profil: Profil;
  resas: Resa[];
  isEditing = false;

  constructor(private profilService: ProfilService,
              public carService: CarService,
              private bookingService: BookingService,
              private router: Router) { }

  ngOnInit() {
    if (localStorage.length === 0) {
      this.router.navigateByUrl('/login');
    } else {
      this.getProfile();
    }
  }

  getProfile() {
    this.profilService.getProfil().subscribe(
      data => {
        this.profil = data.content.user;
        this.resas = data.content.bookings.slice(0, 4);
      },
      err => {
        console.log(err);
      });
  }


  editProfile() {
    this.isEditing = true;
  }

  saveProfil() {
    this.profilService.updateProfil(this.profil).subscribe(
      () => {
        this.isEditing = false;
      }
    );
  }


  editResa(event) {
    this.bookingService.closeBooking(event.idBooking);
  }
}
