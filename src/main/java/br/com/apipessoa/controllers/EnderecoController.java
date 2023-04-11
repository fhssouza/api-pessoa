package br.com.apipessoa.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.apipessoa.entities.Endereco;
import br.com.apipessoa.services.EnderecoService;

@RestController
@RequestMapping("/api/endereco")
public class EnderecoController {

	@Autowired
	private EnderecoService service;
	
	@GetMapping
	public ResponseEntity<List<Endereco>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}

}
