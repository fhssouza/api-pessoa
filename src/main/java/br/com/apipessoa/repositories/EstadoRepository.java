package br.com.apipessoa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.apipessoa.entities.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer>{

	
}
