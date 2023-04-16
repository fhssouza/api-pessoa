package br.com.apipessoa.controllers;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.apipessoa.dto.PessoaDTO;
import br.com.apipessoa.dto.PessoaNewDTO;
import br.com.apipessoa.entities.Pessoa;
import br.com.apipessoa.services.PessoaService;

@RestController
@RequestMapping("/api/pessoa")
public class PessoaController {

	@Autowired
	private PessoaService service;
	
	@PostMapping(value = "/cadastrar")
	public ResponseEntity<Void> save(@RequestBody PessoaDTO pessoaDTO){
		Pessoa pessoa = service.fromDTO(pessoaDTO);
		pessoa = service.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PutMapping(value = "/{pessoaId}/editar")
	public ResponseEntity<Void> update(@Valid @RequestBody PessoaDTO objDto, @PathVariable Integer pessoaId){
		Pessoa obj = service.fromDTO(objDto);
		obj.setId(pessoaId);
		obj = service.update(obj);
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping(value = "/{pessoaId}/consultar")
	public ResponseEntity<Pessoa> findById(Integer pessoaId){
		return ResponseEntity.ok().body(service.findById(pessoaId));
	}
	
	@GetMapping(value = "/listar")
	public ResponseEntity<List<Pessoa>> findAll(){
		return ResponseEntity.ok(service.findAll());
	}

	@PostMapping(value = "/cadastrar/endereco")
	public ResponseEntity<Void> save(@RequestBody PessoaNewDTO pessoaDTO){
		Pessoa pessoa = service.fromDTO(pessoaDTO);
		pessoa = service.save(pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(pessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	/*
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deteleById(@PathVariable Integer id) {
		service.deleteById(id);
		return ResponseEntity.noContent().build();
	}*/

}
