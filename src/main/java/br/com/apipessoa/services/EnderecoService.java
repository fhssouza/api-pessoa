package br.com.apipessoa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apipessoa.entities.Endereco;
import br.com.apipessoa.repositories.EnderecoRepository;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;
	
	public List<Endereco> findAll(){
		return repository.findAll();
	}
}
