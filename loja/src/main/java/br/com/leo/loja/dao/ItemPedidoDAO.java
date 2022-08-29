package br.com.leo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.leo.loja.modelo.ItemPedido;

public class ItemPedidoDAO implements DAO<ItemPedido>{
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	
	static {
		emf = Persistence.createEntityManagerFactory("loja-db");
	}
	
	public ItemPedidoDAO(){
		em = emf.createEntityManager();
	}
	
	@Override
	public DAO<ItemPedido> inserir(ItemPedido itemPedido) {
		em.persist(itemPedido);
		return this;
	}

	@Override
	public void inserirAtomico(ItemPedido itemPedido) {
		abrirTransaction().inserir(itemPedido).fecharTransaction();
	}
	
	@Override
	public void atualizar(ItemPedido itemPedido) {
		abrirTransaction();
		itemPedido = em.merge(itemPedido);
		em.flush();
		fecharTransaction();
		
	}

	@Override
	public DAO<ItemPedido> remover(Long id) {
		ItemPedido p = obterPorId(id);
		p = em.merge(p);
		em.remove(p);
		return this;
	}

	@Override
	public void removerAtomico(Long id) {
		abrirTransaction().remover(id).fecharTransaction();
	}

	@Override
	public ItemPedido obterPorId(Long id) {
		ItemPedido p = em.find(ItemPedido.class, id);
		return p;
	}
	
	public List<ItemPedido> obterPorPedido(Long idPedido) {
String jpql = "SELECT e FROM ItemPedido e WHERE e.pedido.id = :pedido";
		
		return em.createQuery(jpql, ItemPedido.class).setParameter("pedido", idPedido).getResultList();
	}

	@Override
	public List<ItemPedido> obterTodos(int quantidade, int deslocamento) {
		String jpql = "select e from ItemPedido e";
		TypedQuery<ItemPedido> tq = em.createQuery(jpql, ItemPedido.class);
		
		return tq.setMaxResults(quantidade).setFirstResult(deslocamento).getResultList();
	}

	@Override
	public List<ItemPedido> obterTodos() {
		String jpql = "select e from ItemPedido e";
		return em.createQuery(jpql, ItemPedido.class).getResultList();
	}

	@Override
	public DAO<ItemPedido> abrirTransaction() {
			em.getTransaction().begin();
		return this;
	}

	@Override
	public DAO<ItemPedido> fecharTransaction() {
		em.getTransaction().commit();
		return this;
	}

	@Override
	public void fecharManager() {
		em.close();
		
	}

}
