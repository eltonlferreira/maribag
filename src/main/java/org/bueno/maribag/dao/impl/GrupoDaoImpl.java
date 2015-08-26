package org.bueno.maribag.dao.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bueno.maribag.dao.GrupoDaoLocal;
import org.bueno.maribag.dao.generic.AbstractDao;
import org.bueno.maribag.exception.DaoException;
import org.bueno.maribag.model.Grupo;

/**
 * EJB que implementa o DAO de Grupos
 * 
 * @author Bueno
 *
 */
@Stateless
public class GrupoDaoImpl extends AbstractDao<Grupo>implements GrupoDaoLocal {

	@PersistenceContext // Injetado
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public GrupoDaoImpl() {
		super(Grupo.class);
	}

	/**
	 * @see org.bueno.maribag.dao.impl.GrupoDao#save(org.bueno.maribag.model.Grupo)
	 */
	@Override
	public void save(Grupo grupo) {
		if (grupo.getId() == null) {
			insert(grupo);
		} else {
			Grupo grupoToSave = find(grupo.getId());
			edit(grupoToSave);
		}
	}

	/**
	 * @see org.bueno.maribag.dao.impl.GrupoDao#findGrupoByNome(java.lang.String)
	 */
	@Override
	public Grupo findGrupoByNome(String nomeGrupo) throws DaoException {
		try {
			TypedQuery<Grupo> query = getEntityManager().createQuery("Select o from Grupo o where o.nome = :pNome",
					Grupo.class);
			query.setParameter("pNome", nomeGrupo);
			return query.getSingleResult();
		} catch (NoResultException | NonUniqueResultException e) {
			return null;
		}
	}

}
