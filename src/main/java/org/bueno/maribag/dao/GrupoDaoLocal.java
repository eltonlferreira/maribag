package org.bueno.maribag.dao;

import javax.ejb.Local;

import org.bueno.maribag.dao.generic.DaoLocal;
import org.bueno.maribag.exception.DaoException;
import org.bueno.maribag.model.Grupo;

/**
 * Interface local que define o DAO de Grupo
 * 
 * @author Bueno
 *
 */

@Local // expoe métodos para acesso local
public interface GrupoDaoLocal extends DaoLocal<Grupo> {

	/**
	 * Método que retorna um grupo
	 * 
	 * @param nomeGrupo
	 * @return
	 * @throws DaoException
	 */
	Grupo findGrupoByNome(String nomeGrupo) throws DaoException;

}