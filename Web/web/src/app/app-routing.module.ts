import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginPageComponent} from "./login-page/login-page.component";
import {HomeComponent} from "./home/home.component";
import {ProfilComponent} from "./profil/profil.component";

const routes: Routes = [
  {path: 'login', component : LoginPageComponent},
  {path: 'profil', component : ProfilComponent},
  {path: '', component: HomeComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
