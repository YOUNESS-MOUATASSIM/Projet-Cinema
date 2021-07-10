import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {
  public host:string="http://localhost:8080";
  constructor(private http:HttpClient) { }
  
  /*public authentifier(auth :any){
    return this.http.post(this.host+"/authentication",auth);
  }*/
}
