package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;

import com.example.demo.entities.Film;
import com.example.demo.entities.Salle;
import com.example.demo.entities.Ticket;
import com.example.demo.service.ICinemaInitService;

@SpringBootApplication
public class Tp2SpringCinemaApplication implements CommandLineRunner {
	@Autowired
    private ICinemaInitService iCinemaInitService ;
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration;
	
	public static void main(String[] args) {
		SpringApplication.run(Tp2SpringCinemaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		this.repositoryRestConfiguration.exposeIdsFor(Film.class,Salle.class,Ticket.class);
		
		iCinemaInitService.initVilles();
		iCinemaInitService.initCinemas();
		iCinemaInitService.initSalles();
		iCinemaInitService.initPlaces();
		iCinemaInitService.initSeances();
		iCinemaInitService.initCategories();
		iCinemaInitService.initFilms();
		iCinemaInitService.initProjections();
		
		iCinemaInitService.initTickets();
	
        
	}

}
