package org.bueno.maribag.data.impl;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.bueno.maribag.data.AbstractDao;
import org.bueno.maribag.model.Grupo;

@Stateless
public class GrupoDaoImpl extends AbstractDao<Grupo> {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	protected EntityManager getEntityManager() {
		return entityManager;
	}

	public GrupoDaoImpl() {
		super(Grupo.class);
	}

	public void save(Grupo grupo) {
		if (grupo.getId() == null) {
			insert(grupo);
		} else {
			Grupo grupoToSave = find(grupo.getId());
			edit(grupoToSave);
		}
	}

	public Grupo findGrupoByNome(String nomeGrupo) {
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
