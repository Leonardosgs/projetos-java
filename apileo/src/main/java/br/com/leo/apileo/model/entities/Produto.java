package br.com.leo.apileo.model.entities;

import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Entity
@Table(name = "produtos")
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotBlank
	private String nome;
	
	@Min(0)
	private BigDecimal preco;
	
	@Min(0)
	@Max(1)
	private double desconto;
	
	private LocalDate dataCriacao;
	
	private LocalDate dataExclusao;

	public Produto(String nome, BigDecimal preco, double desconto) {
		dataCriacao = LocalDate.now();
		this.nome = nome;
		setDesconto(desconto);
		setPreco(preco);
	}
	public Produto() {
		dataCriacao = LocalDate.now();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public BigDecimal getPreco() {
		return preco;
	}

	public void setPreco(BigDecimal preco) {
		this.preco = preco;
	}

	public double getDesconto() {
		return desconto;
	}

	public void setDesconto(double desconto) {
		this.desconto = desconto;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public LocalDate getDataExclusao() {
		return dataExclusao;
	}
	public void setDataExclusao(LocalDate dataExclusao) {
		this.dataExclusao = dataExclusao;
	}
	
	
	
	
	
	

}
