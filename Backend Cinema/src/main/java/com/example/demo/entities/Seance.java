package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
@Table(name = "SEANCES")
public class Seance implements Serializable{
    
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idSeance;
	@Temporal(TemporalType.TIME)
	private Date heureDebut;
	
	@OneToMany
	private Collection<Projection> projections;
}
