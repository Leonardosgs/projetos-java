package br.com.leo.loja.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import br.com.leo.loja.modelo.Categoria;
import br.com.leo.loja.modelo.Produto;

public class ProdutoDAO implements DAO<Produto>{
	
	private static EntityManagerFactory emf;
	private EntityManager em;
	
	
	static {
		emf = Persistence.createEntityManagerFactory("loja-db");
	}
	
	public ProdutoDAO(){
		em = emf.createEntityManager();
	}
	
	@Override
	public DAO<Produto> inserir(Produto produto) {
		em.persist(produto);
		return this;
	}

	@Override
	public void inserirAtomico(Produto produto) {
		abrirTransaction().inserir(produto).fecharTransaction();
	}
	
	@Override
	public void atualizar(Produto produto) {
		abrirTransaction();
		produto = em.merge(produto);
		em.flush();
		fecharTransaction();
		
	}

	@Override
	public DAO<Produto> remover(Long id) {
		Produto p = obterPorId(id);
		p = em.merge(p);
		em.remove(p);
		return this;
	}

	@Override
	public void removerAtomico(Long id) {
		abrirTransaction().remover(id).fecharTransaction();
	}

	@Override
	public Produto obterPorId(Long id) {
		Produto p = em.find(Produto.class, id);
		return p;
	}

	public List<Produto> obterPorNome(String nome) {
		String formatado = "'%" + nome + "%'";
		String jpql = "SELECT e FROM Produto e WHERE e.nome LIKE " + formatado;
		
		return em.createQuery(jpql, Produto.class).getResultList();
	}
	
	public List<Produto> obterPorCategoria(String nomeCategoria) {
		String jpql = "SELECT e FROM Produto e WHERE e.categoria.nome = :categoria";
		
		return em.createQuery(jpql, Produto.class).setParameter("categoria", nomeCategoria).getResultList();
	}

	@Override
	public List<Produto> obterTodos(int quantidade, int deslocamento) {
		String jpql = "select e from Produto e";
		TypedQuery<Produto> tq = em.createQuery(jpql, Produto.class);
		
		return tq.setMaxResults(quantidade).setFirstResult(deslocamento).getResultList();
	}

	@Override
	public List<Produto> obterTodos() {
		String jpql = "select e from Produto e";
		return em.createQuery(jpql, Produto.class).getResultList();
	}

	@Override
	public DAO<Produto> abrirTransaction() {
			em.getTransaction().begin();
		return this;
	}

	@Override
	public DAO<Produto> fecharTransaction() {
		em.getTransaction().commit();
		return this;
	}

	@Override
	public void fecharManager() {
		em.close();
		
	}

}
