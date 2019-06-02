import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import {FontAwesomeModule} from "@fortawesome/angular-fontawesome";
import {FormsModule} from "@angular/forms";
import { HomeComponent } from './home/home.component';
import { AgmCoreModule } from '@agm/core';
import { NavbarComponent } from './navbar/navbar.component';
import { CarInfoComponent } from './car-info/car-info.component';
import {ConnexionService} from "./connexion.service";
import {HttpClientModule} from "@angular/common/http";



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomeComponent,
    NavbarComponent,
    CarInfoComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAbtGPghuLoSfx-xpNdiu_DqUg3L5VlJRI'
    })
  ],
  providers: [
    ConnexionService,
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
