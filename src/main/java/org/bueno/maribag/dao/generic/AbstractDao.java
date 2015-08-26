package org.bueno.maribag.dao.generic;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaQuery;

/**
 * Classe Abstrata implementa o interface do DAO generico
 * 
 * @author Bueno
 *
 * @param <T>
 */
public abstract class AbstractDao<T> implements DaoLocal<T> {

	private final Class<T> entityClass;

	/**
	 * Construtor utilizado para injetar a classe do DAO
	 * 
	 * @param entityClass
	 *            Classe utilizada nos metodos.
	 */
	public AbstractDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

	/**
	 * Método abstrato para ser implementado por cada DAO concreto.
	 * 
	 * @return EntityManager para controle de entidade JPA
	 */
	protected abstract EntityManager getEntityManager();

	protected void insert(T entity) {
		getEntityManager().persist(entity);
	}

	protected void edit(T entity) {
		getEntityManager().merge(entity);
	}

	@Override
	public abstract void save(T entity);

	/**
	 * @see org.bueno.maribag.dao.generic.Dao#delete(T)
	 */
	@Override
	public void delete(T entity) {
		getEntityManager().remove(getEntityManager().merge(entity));
	}

	/**
	 * @see org.bueno.maribag.dao.generic.Dao#find(java.lang.Object)
	 */
	@Override
	public T find(Object id) {
		return getEntityManager().find(entityClass, id);
	}

	/**
	 * @see org.bueno.maribag.dao.generic.Dao#findAll()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findAll() {
		CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		return getEntityManager().createQuery(cq).getResultList();
	}

	/**
	 * @see org.bueno.maribag.dao.generic.Dao#findRange(int[])
	 */
	@Override
	@SuppressWarnings("unchecked")
	public List<T> findRange(int[] range) {
		final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		cq.select(cq.from(entityClass));
		final javax.persistence.Query q = getEntityManager().createQuery(cq);
		q.setMaxResults(range[1] - range[0]);
		q.setFirstResult(range[0]);
		return q.getResultList();
	}

	/**
	 * @see org.bueno.maribag.dao.generic.Dao#count()
	 */
	@Override
	@SuppressWarnings("unchecked")
	public int count() {
		final CriteriaQuery cq = getEntityManager().getCriteriaBuilder().createQuery();
		final javax.persistence.criteria.Root<T> rt = cq.from(entityClass);
		cq.select(getEntityManager().getCriteriaBuilder().count(rt));
		final Query q = getEntityManager().createQuery(cq);
		return ((Long) q.getSingleResult()).intValue();
	}
}