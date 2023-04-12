package br.com.apipessoa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.apipessoa.dto.PessoaNewDTO;
import br.com.apipessoa.entities.Cidade;
import br.com.apipessoa.entities.Endereco;
import br.com.apipessoa.entities.Pessoa;
import br.com.apipessoa.repositories.EnderecoRepository;
import br.com.apipessoa.repositories.PessoaRepository;
import br.com.apipessoa.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	
	public List<Pessoa> findAll(){
		return repository.findAll();
	}
	
	public Pessoa findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Não foi possível localizar ID: " + id + ", Tipo: " + Pessoa.class.getName()));
	}
	
	public Pessoa save(Pessoa obj) {
		obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}
	
	public Pessoa fromDTO(PessoaNewDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa(null, pessoaDTO.getNome(), pessoaDTO.getDataNascimento());
		Cidade cidade = new Cidade(pessoaDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, pessoaDTO.getLogradouro(), pessoaDTO.getCep(), pessoaDTO.getNumero(), pessoa, cidade);
		pessoa.getEnderecos().add(endereco);
		return pessoa;
	}
}
