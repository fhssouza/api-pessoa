package br.com.apipessoa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apipessoa.entities.Estado;
import br.com.apipessoa.services.EstadoService;

@RestController
@RequestMapping("/api/estado")
public class EstadoController {

	@Autowired
	private EstadoService service;
	
	@GetMapping
	public ResponseEntity<List<Estado>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}

}
