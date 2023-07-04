package com.EvanGardner.springboot.backend.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.EvanGardner.springboot.backend.models.dao.IPersonaDao;
import com.EvanGardner.springboot.backend.models.entity.Persona;

@Service
public class PersonaService implements iPersonaService{

	@Autowired
	private IPersonaDao personaDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Persona> findAll() {
		return (List<Persona>) personaDao.findAll();
	}
	@Override
	@Transactional
	public Persona save(Persona persona) {
		return personaDao.save(persona);
	}
	@Override
	@Transactional(readOnly = true)
	public Persona findById(Long id) {
		return personaDao.findById(id).orElse(null);
	}
	@Override
	@Transactional
	public void delete(Long id) {
		personaDao.deleteById(id);
		
	}

}
