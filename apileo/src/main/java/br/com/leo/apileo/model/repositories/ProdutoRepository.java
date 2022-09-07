package br.com.leo.apileo.model.repositories;

import org.springframework.data.repository.CrudRepository;

import br.com.leo.apileo.model.entities.Produto;

public interface ProdutoRepository  extends CrudRepository<Produto, Long>{
	
}
