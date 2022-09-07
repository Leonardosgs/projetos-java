package br.com.leo.apileo.Controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.leo.apileo.model.entities.Cliente;
import br.com.leo.apileo.model.repositories.ClienteRepository;

@RestController
@RequestMapping("/cliente")
public class ClienteController {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@PostMapping
	public Cliente novoCliente(@Valid Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@GetMapping
	public Iterable<Cliente> obterCliente() {
		return clienteRepository.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/{id}")
	public Optional<Cliente> obterProId(@Valid @PathVariable Long id) {
		return clienteRepository.findById(id);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
	public void deletarPorId(@Valid @PathVariable Long id) {
		clienteRepository.deleteById(id);
	}
	
	@RequestMapping(method = RequestMethod.PUT, path = "/{id}")
	public Cliente alterarCliente(@Valid Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}
	
	@RequestMapping(method = RequestMethod.PATCH, path = "/{id}")
	public Cliente alterarParcialCliente(@Valid Cliente cliente) {
		clienteRepository.save(cliente);
		return cliente;
	}

}
