package br.com.apipessoa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apipessoa.entities.Cidade;
import br.com.apipessoa.services.CidadeService;

@RestController
@RequestMapping("/api/cidade")
public class CidadeController {

	@Autowired
	private CidadeService service;
	
	@GetMapping
	public ResponseEntity<List<Cidade>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}

}
