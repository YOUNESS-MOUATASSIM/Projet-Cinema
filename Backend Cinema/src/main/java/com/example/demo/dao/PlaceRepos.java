package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.Place;


@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface PlaceRepos extends JpaRepository<Place,Long> {

}
