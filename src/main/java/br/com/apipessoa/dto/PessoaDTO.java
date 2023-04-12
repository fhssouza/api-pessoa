package br.com.apipessoa.dto;

import java.io.Serializable;
import java.util.Date;

import br.com.apipessoa.entities.Pessoa;

public class PessoaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	private String nome;
	private Date dataNascimento;

	public PessoaDTO() {
	}

	public PessoaDTO(Pessoa entity) {
		id = entity.getId();
		nome = entity.getNome();
		dataNascimento = entity.getDataNascimento();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

}
