package br.com.leo.apileo.model.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.leo.apileo.model.entities.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
