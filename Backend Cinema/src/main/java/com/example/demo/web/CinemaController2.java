package com.example.demo.web;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CategorieRepos;
import com.example.demo.dao.CinemaRepos;
import com.example.demo.dao.FilmRepos;
import com.example.demo.dao.PlaceRepos;
import com.example.demo.dao.ProjectionRepos;
import com.example.demo.dao.SalleRepos;
import com.example.demo.dao.SeanceRepos;
import com.example.demo.dao.TicketRepos;
import com.example.demo.dao.VilleRepos;
import com.example.demo.entities.Cinema;
import com.example.demo.entities.Film;
import com.example.demo.entities.Salle;
import com.example.demo.entities.Ville;


@Controller(value = "/")
public class CinemaController2 {
	  @Autowired
	  private VilleRepos villeRepos;
	  @Autowired
	  private CinemaRepos cinemaRepos;
	  @Autowired
	  private SalleRepos salleRepos;
	  @Autowired
	  private PlaceRepos placeRepos;
	  @Autowired
	  private SeanceRepos SeanceRepos;
	  @Autowired
	  private CategorieRepos categorieRepos;
	  @Autowired
	  private FilmRepos filmRepos;
	  @Autowired
	  private ProjectionRepos projectionRepos;
	  @Autowired
	  private TicketRepos ticketRepos;
	  
	 
	  @GetMapping(path="/index")
	   public String index() {
		
		   return "index";
	   }
	   
	   @GetMapping(path="/cinema")
	   public String listVilles(Model model,
			   @RequestParam(name="idVille" ,required = false)Long idVille,
			   @RequestParam(name="idCinema" ,required = false)Long idCinema
			   ) {
		   List<Ville> Villes=this.villeRepos.findAll();
		   Ville currentVille = null;
		   Cinema currentCinema=null;
		   Collection<Cinema> cinemas=null;
		   Collection<Salle> salles=null;
		   if(idVille != null ) {
			  currentVille=this.villeRepos.findById(idVille).get();
		      cinemas= currentVille.getCinemas();
		      currentCinema=this.cinemaRepos.findById(idCinema).get();
		      salles=currentCinema.getSalles();
		   }
		   model.addAttribute("villes",Villes);
		   model.addAttribute("currentVille", idVille);
		   model.addAttribute("cinemas", cinemas);
		   model.addAttribute("salles", salles);
		  
		   return "cinema";
	   }
	   
}
