package br.com.apipessoa.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.apipessoa.dto.PessoaDTO;
import br.com.apipessoa.dto.PessoaNewDTO;
import br.com.apipessoa.dto.PessoaNewEnderecoDTO;
import br.com.apipessoa.entities.Cidade;
import br.com.apipessoa.entities.Endereco;
import br.com.apipessoa.entities.Pessoa;
import br.com.apipessoa.repositories.EnderecoRepository;
import br.com.apipessoa.repositories.PessoaRepository;
import br.com.apipessoa.services.exceptions.DataIntegrityException;
import br.com.apipessoa.services.exceptions.ObjectNotFoundException;

@Service
public class PessoaService {

	@Autowired
	private PessoaRepository repository;

	@Autowired
	private EnderecoRepository enderecoRepository;

	public List<Pessoa> findAll() {
		return repository.findAll();
	}

	public Pessoa findById(Integer id) {
		return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException(
				"Não foi possível localizar ID: " + id + ", Tipo: " + Pessoa.class.getName()));
	}

	public Pessoa save(Pessoa obj) {
		//obj.setId(null);
		obj = repository.save(obj);
		enderecoRepository.saveAll(obj.getEnderecos());
		return obj;
	}

	public Pessoa update(Pessoa pessoa) {
		Pessoa newPessoa = findById(pessoa.getId());
		updateDate(newPessoa, pessoa);
		return repository.save(newPessoa);
	}

	public void deleteById(Integer id) {
		findById(id);
		try {
			repository.deleteById(id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é posível excluir porque há endereços relacionados");
		}
	}

	public Pessoa fromDTO(PessoaDTO objDto) {
		return new Pessoa(null, objDto.getNome(), objDto.getDataNascimento());
	}

	public Pessoa fromDTO(PessoaNewDTO pessoaDTO) {
		Pessoa pessoa = new Pessoa(null, pessoaDTO.getNome(), pessoaDTO.getDataNascimento());
		Cidade cidade = new Cidade(pessoaDTO.getCidadeId(), null, null);
		Endereco endereco = new Endereco(null, pessoaDTO.getLogradouro(), pessoaDTO.getCep(), pessoaDTO.getNumero(),
				pessoa, cidade);
		pessoa.getEnderecos().add(endereco);
		return pessoa;
	}
	
	public Pessoa fromDTO(Integer pessoaId, PessoaNewEnderecoDTO pessoaNewEnderecoDTO) {
		Pessoa pessoa = findById(pessoaId);
		
		Endereco endereco = new Endereco();
		endereco.setPessoa(pessoa);
		endereco.setLogradouro(pessoaNewEnderecoDTO.getLogradouro());
		endereco.setCep(pessoaNewEnderecoDTO.getCep());
		endereco.setNumero(pessoaNewEnderecoDTO.getNumero());
		endereco.setCidade(new Cidade(pessoaNewEnderecoDTO.getCidadeId(), null, null));
				
		pessoa.getEnderecos().add(endereco);
		
		enderecoRepository.saveAll(pessoa.getEnderecos());
		
		return pessoa;
	}

	
	private void updateDate(Pessoa newObj, Pessoa obj) {
		newObj.setNome(obj.getNome());
		newObj.setDataNascimento(obj.getDataNascimento());
	}
}
