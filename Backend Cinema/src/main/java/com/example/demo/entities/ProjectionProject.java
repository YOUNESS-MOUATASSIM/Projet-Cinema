package com.example.demo.entities;

import java.util.Collection;
import java.util.Date;
import org.springframework.data.rest.core.config.Projection;

@Projection(name = "proj1",types = {com.example.demo.entities.Projection.class})
public interface ProjectionProject {

	public Long getIdProjection();
	public double getPrix();
	public Date getDateProjection();
	public Salle getSalle();
	public Film getFilm();
	public Seance getSeance();
	
	public Collection<Ticket> getTickets();
	
}
