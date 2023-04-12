package br.com.apipessoa.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apipessoa.dto.PessoaNewDTO;
import br.com.apipessoa.entities.Pessoa;
import br.com.apipessoa.services.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;
	
	@GetMapping
	public ResponseEntity<List<Pessoa>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> findById(Integer id){
		return ResponseEntity.ok().body(service.findById(id));
	}
	
	@PostMapping(value = "/cadastro")
	public ResponseEntity<Void> save(@RequestBody PessoaNewDTO pessoaDTO){
		Pessoa pessoa = service.fromDTO(pessoaDTO);
		pessoa = service.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

}
