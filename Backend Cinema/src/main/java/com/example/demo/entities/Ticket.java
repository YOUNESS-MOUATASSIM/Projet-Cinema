package com.example.demo.entities;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
@Table(name = "TICKETS")
public class Ticket implements Serializable {

	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idTicket;
	@Column(length = 50)
	private String nomClient;
	private double prix;
	@Column(unique = false ,nullable = true)
	private Integer codePaiement;
	private boolean reserve;
	
	@ManyToOne
	@JoinColumn(name = "idProjection")
	@JsonProperty(access = Access.WRITE_ONLY)
	private Projection projection;
	
	@ManyToOne
	@JoinColumn(name = "idPlace")
	private Place place;
}
