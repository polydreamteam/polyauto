import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {CarService} from '../services/car.service';
import {faCar, faUser} from '@fortawesome/free-solid-svg-icons';


@Component({
  selector: 'app-research-modal',
  templateUrl: './research-modal.component.html',
  styleUrls: ['./research-modal.component.scss']
})
export class ResearchModalComponent implements OnInit {

  @Output()
  visible = new EventEmitter();
  @Output()
  researched = new EventEmitter<string[]>();
  faCar = faCar;
  model: number;
  status = '1';


  constructor(public carService: CarService) {
  }

  ngOnInit(): void {
  }

  setVisible() {
    this.visible.emit();
  }

  research() {
    this.researched.emit([this.status, (this.model || '').toString()]);
    this.setVisible();
  }

}
