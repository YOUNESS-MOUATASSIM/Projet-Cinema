import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { tick } from '@angular/core/testing';
import { CinemaService } from '../services/cinema.service';

@Component({
  selector: 'app-cinema',
  templateUrl: './cinema.component.html',
  styleUrls: ['./cinema.component.css']
})
export class CinemaComponent implements OnInit {
  public villes :any;
  public salles: any;
  public cinemas:any;
  public currentVille:any ;
  public currentCinema:any;
  public ticketsAPayer: any;
  public currentProjection: any;
  public selectedTickets: any[] = [];
  

  constructor(public cinemaService:CinemaService) { }

  ngOnInit(): void {
    this.cinemaService.getVilles()
    .subscribe(data=>{
         this.villes=data;
    },err=>{
         console.log(err);
    })
  }
  public onGetCinemas(v :any){
     this.currentVille=v;
     this.salles=undefined;
     this.cinemaService.getCinemas(v)
     .subscribe(data=>{
      this.cinemas=data;
      },err=>{
      console.log(err);
 })
  }
  public onGetSalles(c:any){
    this.currentCinema=c;
    this.cinemaService.getSalles(c)
    .subscribe(data=>{
      this.salles=data;
      this.salles._embedded.salles.forEach((salle: any) => {
        this.cinemaService.getProjections(salle)
        .subscribe(data2=>{
          salle.projections=data2;
          },err=>{
          console.log(err);
          })
        })
      },err=>{
      console.log(err);
    })
  }
  public onGetTicketPlace(projection:any){
     
     this.currentProjection=projection;
     this.cinemaService.getTicketsPlaces(projection)
     .subscribe(data=>{
      this.currentProjection.tickets=data;
      this.selectedTickets=[];
      },err=>{
      console.log(err);
  })
  
}
public onSelectTicket(ticket:any){
 
  if(!ticket.selected){
    ticket.selected=true;
    this.selectedTickets.push(ticket);
    
  }
  else{
    ticket.selected=false;
    this.selectedTickets.splice(this.selectedTickets.indexOf(ticket),1);
  }
  
  
}
public getTicketClass(ticket:any){
  let str="btn ticket ";
  if(ticket.reserve){
    str+="btn-danger";
  }
  else if(ticket.selected){
    str+="btn-warning";
  }
  else{
    str+="btn-success";
  }
  return str;
}
public onPayeTickets(form :any){
  let ticket: any[]=[];
  this.selectedTickets.forEach(t =>{
    ticket.push(t.idTicket);
    
  });
   form.idTickets=ticket;
  
   
   this.cinemaService.payerTickets(form)
   .subscribe(data=>{
    alert("Tickets réservés avec succès")
    this.onGetTicketPlace(this.currentProjection);
    },err=>{
    console.log(err);
})
}
}


