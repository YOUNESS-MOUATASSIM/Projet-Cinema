package com.example.demo.dao;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.demo.entities.Ville;

@RepositoryRestResource
@CrossOrigin("http://localhost:4200")
public interface VilleRepos extends JpaRepository<Ville,Long>{

	Page<Ville> findByNomContains(String kw, PageRequest of);


}
