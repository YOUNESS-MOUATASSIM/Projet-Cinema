package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
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
@Table(name = "CINEMAS")
public class Cinema implements Serializable {
	
@Id
@GeneratedValue(strategy =GenerationType.IDENTITY)
private Long idCinema;
@Column(length = 50)
private String nom;
private double longitude ,altitude,  latitude;
private int nbSalles;


@ManyToOne
@JoinColumn(name = "idVille")
private Ville ville;

@OneToMany(mappedBy = "cinema")
private Collection<Salle> salles;


}
