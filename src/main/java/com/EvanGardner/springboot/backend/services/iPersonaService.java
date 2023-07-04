package com.EvanGardner.springboot.backend.services;

import java.util.List;

import com.EvanGardner.springboot.backend.models.entity.Persona;

public interface iPersonaService {
	public List<Persona> findAll();
	
	public Persona save(Persona persona);
	
	public Persona findById(Long id);
	
	public void delete(Long id);
	
	
}
