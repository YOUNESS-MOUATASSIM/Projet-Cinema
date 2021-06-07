package com.example.demo.web;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import com.example.demo.dao.FilmRepos;
import com.example.demo.dao.TicketRepos;
import com.example.demo.entities.Film;
import com.example.demo.entities.Ticket;


import lombok.Data;

@RestController
@CrossOrigin("http://localhost:4200")
public class CinemaController {
	@Autowired
	private FilmRepos filmRepos;
	@Autowired
	private TicketRepos ticketRepos;
	
	@GetMapping(path="/images/{id}",produces =MediaType.IMAGE_JPEG_VALUE)
	public byte[] image(@PathVariable(name = "id")Long id) throws IOException {
		Film film=filmRepos.findById(id).get();
		String nomPhoto=film.getPhoto();
		File file=new File(System.getProperty("user.home")+"/cinemaImages/"+nomPhoto);
		Path path=Paths.get(file.toURI());
		return Files.readAllBytes(path);
		
	}
	@PostMapping("/payerTickets")
	@Transactional
	public List<Ticket> ticketsAcheter(@RequestBody TicketForm ticketForm){
		List<Ticket> listTickets=new ArrayList<>();
		ticketForm.getIdTickets().forEach(id->{
			Ticket ticket=ticketRepos.findById(id).get();
			ticket.setNomClient(ticketForm.getClientNom());
			ticket.setReserve(true);
			ticket.setCodePaiement(ticketForm.getCodePaiement());
			ticketRepos.save(ticket);
			listTickets.add(ticket);
		});
		return listTickets;
	}

}

@Data
class TicketForm{
	private String ClientNom;
	private int codePaiement;
	private List<Long> idTickets=new ArrayList<>();
}
