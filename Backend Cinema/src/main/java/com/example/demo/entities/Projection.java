package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

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
@Table(name = "PROJECTIONS")
public class Projection implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idProjection;
	private double prix;
    private Date dateProjection;
    
    @ManyToOne
    @JsonProperty(access = Access.WRITE_ONLY)
    private Salle salle;
    
    @ManyToOne
    private Film film;
    
    @OneToMany(mappedBy = "projection")
    @JsonProperty(access = Access.WRITE_ONLY)
    private Collection<Ticket> tickets;
    
    @ManyToOne
    @JoinColumn(name = "idSeance")
    private Seance seance;
    
    
}
