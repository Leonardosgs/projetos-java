package br.com.leo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.leo.loja.modelo.Cliente;

public class ClienteDAO implements DAO<Cliente>{
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	
	static {
		emf = Persistence.createEntityManagerFactory("loja-db");
	}
	
	public ClienteDAO(){
		em = emf.createEntityManager();
	}
	
	@Override
	public DAO<Cliente> inserir(Cliente cliente) {
		em.persist(cliente);
		return this;
	}

	@Override
	public void inserirAtomico(Cliente cliente) {
		abrirTransaction().inserir(cliente).fecharTransaction();
	}
	
	@Override
	public void atualizar(Cliente cliente) {
		abrirTransaction();
		cliente = em.merge(cliente);
		em.flush();
		fecharTransaction();
		
	}

	@Override
	public DAO<Cliente> remover(Long id) {
		Cliente p = obterPorId(id);
		p = em.merge(p);
		em.remove(p);
		return this;
	}

	@Override
	public void removerAtomico(Long id) {
		abrirTransaction().remover(id).fecharTransaction();
	}

	@Override
	public Cliente obterPorId(Long id) {
		Cliente p = em.find(Cliente.class, id);
		return p;
	}

	public List<Cliente> obterPorNome(String nome) {
		String formatado = "'%" + nome + "%'";
		String jpql = "SELECT e FROM Cliente e WHERE e.nome LIKE " + formatado;
		
		return em.createQuery(jpql, Cliente.class).getResultList();
	}

	@Override
	public List<Cliente> obterTodos(int quantidade, int deslocamento) {
		String jpql = "select e from Cliente e";
		TypedQuery<Cliente> tq = em.createQuery(jpql, Cliente.class);
		
		return tq.setMaxResults(quantidade).setFirstResult(deslocamento).getResultList();
	}

	@Override
	public List<Cliente> obterTodos() {
		String jpql = "select e from Cliente e";
		return em.createQuery(jpql, Cliente.class).getResultList();
	}

	@Override
	public DAO<Cliente> abrirTransaction() {
			em.getTransaction().begin();
		return this;
	}

	@Override
	public DAO<Cliente> fecharTransaction() {
		em.getTransaction().commit();
		return this;
	}

	@Override
	public void fecharManager() {
		em.close();
		
	}

}
