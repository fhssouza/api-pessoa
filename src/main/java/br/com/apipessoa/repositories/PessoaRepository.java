package br.com.apipessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apipessoa.entities.Pessoa;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Integer>{

	
}
