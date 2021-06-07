package com.example.demo.entities;



import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "FILMS")
public class Film implements Serializable{
    @Id
    @GeneratedValue(strategy =GenerationType.IDENTITY)
	private Long idFilm;
    @Column(length = 50)
    private String titre;
    @Column(length = 250)
    private String description;
    @Column(length = 50)
    private String realisateur;
    private Date dateSortie;
    private double duree;
    @Column(length = 200)
    private String photo;
    
	@OneToMany(mappedBy = "film")
	private Collection<Projection> projection;
	
	@ManyToOne
	@JoinColumn(name = "idCategorie")
	private Categorie categorie;
}
