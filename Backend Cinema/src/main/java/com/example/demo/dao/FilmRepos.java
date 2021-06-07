package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.Film;


@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface FilmRepos extends JpaRepository<Film,Long>{

}
