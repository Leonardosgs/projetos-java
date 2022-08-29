package br.com.leo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.leo.loja.modelo.Cliente;
import br.com.leo.loja.modelo.Pedido;

public class PedidoDAO implements DAO<Pedido>{
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	
	static {
		emf = Persistence.createEntityManagerFactory("loja-db");
	}
	
	public PedidoDAO(){
		em = emf.createEntityManager();
	}
	
	@Override
	public DAO<Pedido> inserir(Pedido pedido) {
		em.persist(pedido);
		return this;
	}

	@Override
	public void inserirAtomico(Pedido pedido) {
		abrirTransaction().inserir(pedido).fecharTransaction();
	}
	
	@Override
	public void atualizar(Pedido pedido) {
		abrirTransaction();
		pedido = em.merge(pedido);
		em.flush();
		fecharTransaction();
		
	}

	@Override
	public DAO<Pedido> remover(Long id) {
		Pedido p = obterPorId(id);
		p = em.merge(p);
		em.remove(p);
		return this;
	}

	@Override
	public void removerAtomico(Long id) {
		abrirTransaction().remover(id).fecharTransaction();
	}

	@Override
	public Pedido obterPorId(Long id) {
		Pedido p = em.find(Pedido.class, id);
		return p;
	}
	
	public List<Pedido> obterPedidosCliente(Cliente cliente) {
String jpql = "SELECT e FROM Pedido e WHERE e.cliente.nome = :cliente";
		
		return em.createQuery(jpql, Pedido.class).setParameter("cliente", cliente.getId()).getResultList();
	}

	@Override
	public List<Pedido> obterTodos(int quantidade, int deslocamento) {
		String jpql = "select e from Pedido e";
		TypedQuery<Pedido> tq = em.createQuery(jpql, Pedido.class);
		
		return tq.setMaxResults(quantidade).setFirstResult(deslocamento).getResultList();
	}

	@Override
	public List<Pedido> obterTodos() {
		String jpql = "select e from Pedido e";
		return em.createQuery(jpql, Pedido.class).getResultList();
	}

	@Override
	public DAO<Pedido> abrirTransaction() {
			em.getTransaction().begin();
		return this;
	}

	@Override
	public DAO<Pedido> fecharTransaction() {
		em.getTransaction().commit();
		return this;
	}

	@Override
	public void fecharManager() {
		em.close();
		
	}

}
