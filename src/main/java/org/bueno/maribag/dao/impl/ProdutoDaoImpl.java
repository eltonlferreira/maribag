package org.bueno.maribag.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bueno.maribag.dao.ProdutoDaoLocal;
import org.bueno.maribag.dao.generic.AbstractDao;
import org.bueno.maribag.exception.DaoException;
import org.bueno.maribag.model.Produto;

/**
 * EJB que implementa o DAO de Produtos
 * 
 * @author Bueno
 *
 */
@Stateless
public class ProdutoDaoImpl extends AbstractDao<Produto>implements ProdutoDaoLocal {

	@PersistenceContext // Injetado
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ProdutoDaoImpl() {
		super(Produto.class);
	}

	/**
	 * @see org.bueno.maribag.dao.impl.ProdutoDao#save(org.bueno.maribag.model.
	 *      Produto)
	 */
	@Override
	public void save(Produto produto) {
		if (produto.getId() == null) {
			insert(produto);
		} else {
			edit(produto);
		}
	}

	/**
	 * @see org.bueno.maribag.dao.impl.ProdutoDao#findProdutoByNome(java.lang.String)
	 */
	@Override
	public Produto findProdutoByNome(String nomeProduto) throws DaoException {
		try {
			TypedQuery<Produto> query = getEntityManager().createQuery("Select o from Produto o where o.nome = :pNome",
					Produto.class);
			query.setParameter("pNome", nomeProduto);
			return query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

}
