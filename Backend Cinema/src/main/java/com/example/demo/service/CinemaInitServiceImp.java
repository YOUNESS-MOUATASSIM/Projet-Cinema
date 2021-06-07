package com.example.demo.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.CategorieRepos;
import com.example.demo.dao.CinemaRepos;
import com.example.demo.dao.FilmRepos;
import com.example.demo.dao.PlaceRepos;
import com.example.demo.dao.ProjectionRepos;
import com.example.demo.dao.SalleRepos;
import com.example.demo.dao.SeanceRepos;
import com.example.demo.dao.TicketRepos;
import com.example.demo.dao.VilleRepos;
import com.example.demo.entities.Categorie;
import com.example.demo.entities.Cinema;
import com.example.demo.entities.Film;
import com.example.demo.entities.Place;
import com.example.demo.entities.Projection;
import com.example.demo.entities.Salle;
import com.example.demo.entities.Seance;
import com.example.demo.entities.Ticket;
import com.example.demo.entities.Ville;

@Service
@Transactional
public class CinemaInitServiceImp implements ICinemaInitService {
	@Autowired
	private VilleRepos villeRepos;
	@Autowired
	private CinemaRepos cinemaRepos;
	@Autowired
	private SalleRepos salleRepos;
	@Autowired
	private PlaceRepos placeRepos;
	@Autowired
	private SeanceRepos seanceRepos;
	@Autowired
	private CategorieRepos categorieRepos;
	@Autowired
	private FilmRepos filmRepos;
	@Autowired
	private ProjectionRepos projectionRepos;
	@Autowired
	private TicketRepos ticketRepos;

	@Override
	public void initVilles() {
		Stream.of("Casablanca","Marrakech","Agadir","Guelmim").forEach(villeNom->{
			Ville nvVille=new Ville();
			nvVille.setNom(villeNom);
			villeRepos.save(nvVille);
			
		});
		
	}

	@Override
	public void initCinemas() {
		villeRepos.findAll().forEach(ville->{
			Stream.of("Mégarama","IMAX","Rialto","Salam","Khayma","Colisée")
			.forEach(cinemaNom->{
				Cinema nvCinema=new Cinema();
				nvCinema.setNom(cinemaNom);
				nvCinema.setNbSalles(2+(int)(Math.random()*2));
				nvCinema.setVille(ville);
				cinemaRepos.save(nvCinema);
			});
		});
		
	}

	@Override
	public void initSalles() {
		cinemaRepos.findAll().forEach(cinema->{
			for(int i=0;i<cinema.getNbSalles();i++) {
				Salle nvSalle=new Salle();
				nvSalle.setNom("Salle-"+(i+1));
				nvSalle.setCinema(cinema);
				nvSalle.setNbPlaces(5+(int)(Math.random()*5));
				salleRepos.save(nvSalle);
			}
			
			
			
		});
		
	}

	@Override
	public void initPlaces() {
		salleRepos.findAll().forEach(salle->{
			for(int i=0;i<salle.getNbPlaces();i++) {
				Place nvPlace=new Place();
	            nvPlace.setNumero(i+1);
	            nvPlace.setSalle(salle);
	            placeRepos.save(nvPlace);
	            
			}
			
			
			
		});
		
	}

	@Override
	public void initSeances() {
		DateFormat dateFormat=new SimpleDateFormat("HH:mm");
		Stream.of("10:00","12:15","14:30","15:45","20:00","20:45")
		.forEach(seance->{
			Seance nvSeance=new Seance();
			try {
				nvSeance.setHeureDebut(dateFormat.parse(seance));
				seanceRepos.save(nvSeance);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});
	}

	@Override
	public void initCategories() {
		Stream.of("Action","Adventure","Drama","Comedy","History")
		.forEach(categorie->{
			Categorie nvCategorie=new Categorie();
			nvCategorie.setNom(categorie);
			categorieRepos.save(nvCategorie);
		});
	}

	@Override
	public void initFilms() {
		double[] durees=new double[] {0.75,1,1.5,2,2.5};
		List<Categorie> categories=categorieRepos.findAll();
		Stream.of("Jocker","Army of the Dead","Oxygen","Without Remorse","Six Underground")
		.forEach(filmTitre->{
			Film nvFilm =new Film();
		    nvFilm.setTitre(filmTitre);
		    nvFilm.setDuree(durees[new Random().nextInt(durees.length)]);
		    nvFilm.setPhoto(filmTitre.replaceAll(" ","")+".jpg");
		    nvFilm.setCategorie(categories.get(new Random().nextInt(categories.size())));
		    filmRepos.save(nvFilm);
		});
	}

	@Override
	public void initProjections() {
		double[] prix=new double[] {35,45,50,60,80,85,90};
		List<Film> films=filmRepos.findAll();
		villeRepos.findAll().forEach(ville->{
		   ville.getCinemas().forEach(cinema->{
			   cinema.getSalles().forEach(salle->{
				   int index=new Random().nextInt(films.size());
				   Film film=films.get(index);
				   
					   seanceRepos.findAll().forEach(seance->{
						   Projection nvProjection=new Projection();
						   nvProjection.setDateProjection(new Date());
						   nvProjection.setFilm(film);
						   nvProjection.setPrix(prix[new Random().nextInt(prix.length)]);
						   nvProjection.setSalle(salle);
						   nvProjection.setSeance(seance);
						   projectionRepos.save(nvProjection);
					   });
				
			   });
		   });
		});
	}

	@Override
	public void initTickets() {
		projectionRepos.findAll().forEach(projection->{
			
			projection.getSalle().getPlaces().forEach(place->{
				
				Ticket nvTicket=new Ticket();
				
				nvTicket.setPlace(place);
				nvTicket.setPrix(projection.getPrix());
				nvTicket.setProjection(projection);
				nvTicket.setReserve(false);
				ticketRepos.save(nvTicket);
			
				
			});
		});
	}

}
