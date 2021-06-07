import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AuthenticationComponent } from './authentication/authentication.component';
import { CinemaComponent } from './cinema/cinema.component';

const routes: Routes = [
  {
    path:"authentication",
    component : AuthenticationComponent 
  },
  {
    path:"cinema",
    component : CinemaComponent
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
