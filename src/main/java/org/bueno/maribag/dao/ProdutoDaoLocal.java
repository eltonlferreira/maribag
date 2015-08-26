package org.bueno.maribag.dao;

import javax.ejb.Local;

import org.bueno.maribag.dao.generic.DaoLocal;
import org.bueno.maribag.exception.DaoException;
import org.bueno.maribag.model.Produto;

/**
 * Interface local que define o DAO de Produto
 * 
 * @author Bueno
 *
 */

@Local // expoe métodos para acesso local
public interface ProdutoDaoLocal extends DaoLocal<Produto> {
	/**
	 * Método que retorna um produto por nome
	 * 
	 * @param nomeProduto
	 * @return Produto
	 * @throws DaoException
	 */
	Produto findProdutoByNome(String nomeProduto) throws DaoException;

}