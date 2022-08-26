package br.com.leo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.leo.loja.modelo.Categoria;

public class CategoriaDAO implements DAO<Categoria> {
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	
	static {
		emf = Persistence.createEntityManagerFactory("loja-db");
	}
	
	public CategoriaDAO(){
		em = emf.createEntityManager();
	}
	
	
	@Override
	public DAO<Categoria> inserir(Categoria categoria) {
		em.persist(categoria);
		return this;
	}

	@Override
	public void inserirAtomico(Categoria categoria) {
		abrirTransaction().inserir(categoria).fecharTransaction();	
	}

	@Override
	public void atualizar(Categoria categoria) {
		categoria = em.merge(categoria);
		em.flush();
	}

	@Override
	public DAO<Categoria> remover(Long id) {
		Categoria c = obterPorId(id);
		c = em.merge(c);
		em.remove(c);
		return this;
	}

	@Override
	public void removerAtomico(Long id) {
		abrirTransaction().remover(id).fecharTransaction();
	}

	@Override
	public Categoria obterPorId(Long id) {
		Categoria c = em.find(Categoria.class, id);
		return c;
	}

	@Override
	public List<Categoria> obterTodos(int quantidade, int deslocamento) {
		String jpql = "select e from Categoria e";
		TypedQuery<Categoria> tq = em.createQuery(jpql, Categoria.class);
		
		return tq.setMaxResults(quantidade).setFirstResult(deslocamento).getResultList();
	}

	@Override
	public List<Categoria> obterTodos() {
		String jpql = "select e from Categoria e";
		
		return em.createQuery(jpql, Categoria.class).getResultList();
	}

	@Override
	public DAO<Categoria> abrirTransaction() {
		em.getTransaction().begin();
		return this;
	}

	@Override
	public DAO<Categoria> fecharTransaction() {
		em.getTransaction().commit();
		return this;
	}

	@Override
	public void fecharManager() {
		em.close();
	}
	
	

}
