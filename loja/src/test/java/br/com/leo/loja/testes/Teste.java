package br.com.leo.loja.testes;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import br.com.leo.loja.dao.ClienteDAO;
import br.com.leo.loja.dao.ItemPedidoDAO;
import br.com.leo.loja.dao.PedidoDAO;
import br.com.leo.loja.dao.ProdutoDAO;
import br.com.leo.loja.modelo.ItemPedido;
import br.com.leo.loja.modelo.Pedido;

public class Teste {
	
public static void main(String[] args) {
		
		ProdutoDAO pdao = new ProdutoDAO();
		PedidoDAO pedidao = new PedidoDAO();
		ClienteDAO clidao = new ClienteDAO();
		ItemPedidoDAO ipdao = new ItemPedidoDAO();
		
		Pedido pedido = new Pedido(clidao.obterPorId(1l));
		
		ItemPedido ip1 = new ItemPedido(1, pedido, pdao.obterPorId(10l));
		ItemPedido ip2 = new ItemPedido(2, pedido, pdao.obterPorId(11l));
		ItemPedido ip3 = new ItemPedido(2, pedido, pdao.obterPorId(12l));
		
		pedido.adicionarItem(ip1);
		pedido.adicionarItem(ip2);
		pedido.adicionarItem(ip3);
		
		pedidao.inserirAtomico(pedido);
		
		BigDecimal big = pedidao.valorTotalVendido();
		
		DecimalFormat format = new DecimalFormat("#,###,##0.00");
		
		System.out.println(format.format(big));
		
		
		
		
		
		
		
	
		
		
		
		
		
		
	}

}
