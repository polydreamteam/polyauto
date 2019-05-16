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



@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HomeComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FontAwesomeModule,
    FormsModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyAbtGPghuLoSfx-xpNdiu_DqUg3L5VlJRI'
    })
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
