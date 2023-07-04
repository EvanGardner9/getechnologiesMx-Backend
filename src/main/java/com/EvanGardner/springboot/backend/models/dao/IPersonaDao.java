package com.EvanGardner.springboot.backend.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.EvanGardner.springboot.backend.models.entity.Persona;

public interface IPersonaDao extends CrudRepository<Persona,Long>{

	
}
