package org.bueno.maribag.dao.generic;

import java.util.List;

/**
 * Interface do DAO genérico
 * 
 * @author Bueno
 *
 * @param <T>
 *            Definição do objeto T
 * 
 */
public interface DaoLocal<T> {

	/**
	 * Método genérico para remover uma entidade JPA
	 * 
	 * @param entity
	 *            Entidade JPA
	 */
	void delete(T entity);

	/**
	 * Método genérico para salvar ou editar uma entidade JPA
	 * 
	 * @param entity
	 *            Entidade JPA
	 */
	void save(T entity);

	/**
	 * Método genérico para busca uma entidade JPA por ID
	 * 
	 * @param id
	 *            ID da objeto da entidade JPA
	 */
	T find(Object id);

	/**
	 * Método genérico para buscar todas as entidade JPA, <strong>deve ser usado
	 * com muito cuidado, por trazer todos os objetos</strong>
	 * 
	 * @return Lista contendo todos os objetos
	 */
	List<T> findAll();

	/**
	 * Retorna um range de entidades inicial e final, deve ser passador um array
	 * contendo na primeira posição a posição inicial e na segunda posição o
	 * total de elementos.
	 * 
	 * @param range
	 *            Passa o vetor de duas posições contendo sendo: range[0]
	 *            inicio, range[1] fim.
	 * 
	 * @return Lista contendo todos os objetos entre o inicio e fim
	 */
	List<T> findRange(int[] range);

	/**
	 * Traz o total de itens do repositório da entidade
	 * 
	 * @return O total de registros.
	 */
	int count();

}