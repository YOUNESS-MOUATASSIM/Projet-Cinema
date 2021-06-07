import { Component, OnInit } from '@angular/core';
import { ROUTES } from '@angular/router';
import { AuthenticationService } from '../services/authentication.service';

@Component({
  selector: 'app-authentication',
  templateUrl: './authentication.component.html',
  styleUrls: ['./authentication.component.css']
})
export class AuthenticationComponent implements OnInit {

  constructor(private authenticationService :AuthenticationService) {
    
 }

  ngOnInit(): void {
  }
  public sAuthentifier(form :any){
    console.log(" gooood "+form);
    this.authenticationService.authentifier(form)
    .subscribe(data=>{
     
     
     },err=>{
     console.log(err);
  })
  }
}
