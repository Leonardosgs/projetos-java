package br.com.leo.loja.dao;

import java.util.List;

public interface DAO<E> {
	
	public DAO<E> inserir(E classe);
	
	public void inserirAtomico(E classe);
	
	public void atualizar(E classe);
	
	public DAO<E> remover(Long id);
	
	public void removerAtomico(Long id);
	
	public E obterPorId(Long id);
	
	public List<E> obterTodos(int quantidade, int deslocamento);
	
	public List<E> obterTodos();
	
	public DAO<E> abrirTransaction();
	
	public DAO<E> fecharTransaction();
	
	public void fecharManager();
	

}
