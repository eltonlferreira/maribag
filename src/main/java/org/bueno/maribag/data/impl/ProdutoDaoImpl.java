package org.bueno.maribag.data.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bueno.maribag.data.AbstractDao;
import org.bueno.maribag.model.Produto;

@Stateless
public class ProdutoDaoImpl extends AbstractDao<Produto> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public ProdutoDaoImpl() {
		super(Produto.class);
	}

	public void save(Produto produto) {
		if (produto.getId() == null) {
			insert(produto);
		} else {
			edit(produto);
		}
	}

	public Produto findProdutoByNome(String nomeProduto) {
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
