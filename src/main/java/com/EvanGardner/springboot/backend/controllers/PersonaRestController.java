package com.EvanGardner.springboot.backend.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.EvanGardner.springboot.backend.models.entity.Persona;
import com.EvanGardner.springboot.backend.services.iPersonaService;

@CrossOrigin(origins = {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class PersonaRestController {
	
	@Autowired
	private iPersonaService personaService;
	
	@GetMapping("/personas")
	public List<Persona> index(){
		return personaService.findAll();
		
	}
	
	@GetMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> show(@PathVariable Long id) {
		Persona persona = null;
		Map<String,Object> response = new HashMap<>();
		
		try {
			persona = personaService.findById(id);
		} catch(DataAccessException e){
			response.put("mensaje","Error al realizar la consulta en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		
		}
		
		if (persona == null) {
			response.put("mensaje","La persona ID: ".concat(id.toString().concat(" No existe en la base de datos!")));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Persona>(persona, HttpStatus.OK);
	}
	
	@PostMapping("/personas")
	public ResponseEntity<?> create(@RequestBody Persona persona) {
		Persona personaNew=null;
		Map<String,Object> response = new HashMap<>();
		
		try {
		personaNew = personaService.save(persona);
		
		}catch(DataAccessException e) {
			response.put("mensaje","Error al insertar en la base de datos");
			response.put("error",e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String,Object>>(response, HttpStatus.NOT_FOUND);
			
		}
		response.put("", "Persona creada con exito");
		response.put("persona", personaNew);
		return new ResponseEntity<Map<String,Object>>(response,HttpStatus.CREATED);
	}
	
	@PutMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Persona update(@RequestBody Persona persona, @PathVariable Long id) {
		Persona personaActual = personaService.findById(id);
		personaActual.setApellidoPaterno(persona.getApellidoPaterno());
		personaActual.setApellidoMaterno(persona.getApellidoMaterno());
		personaActual.setNombre(persona.getNombre());
		personaActual.setidentificacion(persona.getidentificacion());
		
		return personaService.save(personaActual);
	}
	
	@DeleteMapping("/personas/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		personaService.delete(id);
	}
}
