package com.example.demo.entities;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
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
@Table(name = "VILLES")
public class Ville implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idVille;
    @Column(length = 50)
    private String nom;
    private double longitude ,altitude,  latitude;
	
    @OneToMany(mappedBy = "ville")
	private Collection<Cinema> cinemas;
}
