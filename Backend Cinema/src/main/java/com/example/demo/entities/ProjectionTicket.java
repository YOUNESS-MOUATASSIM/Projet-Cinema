package com.example.demo.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name ="ticket",types = Ticket.class )
public interface ProjectionTicket {
	
    public Long getIdTicket();
    public String getNomClient();
    public double getPrix();
    public Integer getCodePaiement();
    public boolean getReserve();
    public Place getPlace();
}
