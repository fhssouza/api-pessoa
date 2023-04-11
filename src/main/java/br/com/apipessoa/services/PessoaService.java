package br.com.apipessoa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apipessoa.entities.Pessoa;
import br.com.apipessoa.repositories.PessoaRepository;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	public List<Pessoa> findAll(){
		return repository.findAll();
	}
}
