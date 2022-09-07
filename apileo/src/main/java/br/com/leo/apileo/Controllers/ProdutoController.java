package br.com.leo.apileo.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.leo.apileo.model.entities.Produto;
import br.com.leo.apileo.model.repositories.ProdutoRepository;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@RequestMapping(method = RequestMethod.POST)
	public @ResponseBody Produto novoProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	@GetMapping
	public Iterable<Produto> obterProdutos() {
		return produtoRepository.findAll();
	}
	
	//@GetMapping
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Optional<Produto> obterProId(@Valid @PathVariable Long id) {
		return produtoRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public void deletarPorId(@Valid @PathVariable Long id) {
		produtoRepository.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Produto alterarProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
	
	@PatchMapping("/{id}")
	public Produto alterarParcialProduto(@Valid Produto produto) {
		produtoRepository.save(produto);
		return produto;
	}
}
