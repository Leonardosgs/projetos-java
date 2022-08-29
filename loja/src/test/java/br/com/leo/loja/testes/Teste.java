package br.com.leo.loja.testes;

import br.com.leo.loja.dao.ClienteDAO;
import br.com.leo.loja.dao.ItemPedidoDAO;
import br.com.leo.loja.dao.PedidoDAO;
import br.com.leo.loja.dao.ProdutoDAO;
import br.com.leo.loja.modelo.Cliente;
import br.com.leo.loja.modelo.ItemPedido;
import br.com.leo.loja.modelo.Pedido;
import br.com.leo.loja.modelo.Produto;

public class Teste {
	
public static void main(String[] args) {
		
		ProdutoDAO pdao = new ProdutoDAO();
		PedidoDAO pedidao = new PedidoDAO();
		ClienteDAO clidao = new ClienteDAO();
		ItemPedidoDAO ipdao = new ItemPedidoDAO();
		
		Cliente cliente = clidao.obterPorId(1l);
		
		Produto p1 = pdao.obterPorId(8l);
		Produto p2 = pdao.obterPorId(9l);
		Produto p3 = pdao.obterPorId(10l);
		
		Pedido pedido = new Pedido(cliente);
		
		ItemPedido ip1 = new ItemPedido(1, pedido, p1);
		ItemPedido ip2 = new ItemPedido(1, pedido, p2);
		ItemPedido ip3 = new ItemPedido(1, pedido, p3);
		
		
		pedido.adicionarItem(ip1);
		System.out.println(pedido.getValorTotal());
		
		pedido.adicionarItem(ip2);
		System.out.println(pedido.getValorTotal());
		
		pedido.adicionarItem(ip3);
		System.out.println(pedido.getValorTotal());
		
		pedidao.inserirAtomico(pedido);
		
		
		
		
		
		
		
	
		
		
		
		
		
		
	}

}
