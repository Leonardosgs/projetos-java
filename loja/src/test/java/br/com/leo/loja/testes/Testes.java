package br.com.leo.loja.testes;

import java.util.List;

import br.com.leo.loja.dao.ProdutoDAO;
import br.com.leo.loja.modelo.Produto;

public class Testes {
	public static void main(String[] args) {
		ProdutoDAO pdao = new ProdutoDAO();
		
		
		List<Produto> lista = pdao.obterPorCategoria("Celulares");
		lista.forEach(p -> System.out.println(p.getNome()));
		
		pdao.fecharManager();
		
		
		
	}

}
