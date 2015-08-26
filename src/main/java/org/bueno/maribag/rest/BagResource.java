package org.bueno.maribag.rest;

import javax.ejb.Local;
import javax.ws.rs.core.Response;

import org.bueno.maribag.model.Grupo;
import org.bueno.maribag.model.Produto;

@Local
public interface BagResource {

	/**
	 * Acesso a todos os grupos por um webservice REST
	 * 
	 * @return Response contendo dados da resposta
	 */
	Response findAllGrupos();

	/**
	 * Acesso a todos os produtos por um webservice REST
	 * 
	 * @return Response contendo dados da resposta
	 */
	Response findAllProdutos();

	/**
	 * Acesso a um produto por um ID por um webservice em REST
	 * 
	 * @return Response contendo dados da resposta
	 */
	Response findProdutoById(Long id);

	/**
	 * Acesso a um grupo por um ID por um webservice em REST
	 * 
	 * @return Response contendo dados da resposta
	 */
	Response findGrupoById(Long id);

	/**
	 * Insere um grupo por um webservice em REST
	 * 
	 * @return Response contendo dados da resposta
	 */
	Response salvaGrupo(Grupo grupo);

	/**
	 * Altera um Produto por um webservice em REST
	 * 
	 * @param produto
	 *            dados do produto a ser alterado
	 * @param id
	 *            id do produto a ser alterado
	 * 
	 * @return Response contendo dados da resposta
	 */
	Response updateProduto(Long id, Produto produto);

	/**
	 * Insere um produto por um webservice em REST
	 * 
	 * @param produto
	 *            produto a ser inserido
	 * @return Response contendo dados da resposta
	 */
	Response salvaProduto(Produto produto);

	/**
	 * Insere um produto por um webservice em REST
	 * 
	 * @param id
	 *            id do produto a ser excluido
	 * @return Response contendo dados da resposta
	 */
	Response excluirProduto(Long idProduto);

}