package org.bueno.maribag.rest.impl;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.bueno.maribag.dao.GrupoDaoLocal;
import org.bueno.maribag.dao.ProdutoDaoLocal;
import org.bueno.maribag.model.Grupo;
import org.bueno.maribag.model.Produto;
import org.bueno.maribag.rest.BagResource;

/**
 * EJB para servir de interface com WEBSERVICE
 * 
 * @author Bueno
 *
 */
@SuppressWarnings("serial")
@Path("/")
@Stateless
@Dependent
public class BagResourceImpl implements Serializable, BagResource {

	@Inject
	protected Logger log;

	@Inject
	protected Validator validator;

	@EJB
	private ProdutoDaoLocal produtoDao;
	@EJB
	private GrupoDaoLocal grupoDao;

	public BagResourceImpl() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bueno.maribag.rest.BagResourceI#findAllGrupos()
	 */
	@Override
	@GET
	@Path("/grupos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllGrupos() {
		final List<Grupo> grupos = grupoDao.findAll();
		return Response.ok().entity(grupos).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bueno.maribag.rest.BagResourceI#findAllProdutos()
	 */
	@Override
	@GET
	@Path("/produtos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findAllProdutos() {
		final List<Produto> produtos = produtoDao.findAll();
		return Response.ok().entity(produtos).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bueno.maribag.rest.BagResourceI#findProdutoById(java.lang.Long)
	 */
	@Override
	@GET
	@Path("/produtos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findProdutoById(@PathParam("id") Long id) {
		final Produto produto = produtoDao.find(id);
		if (produto == null) {
			log.log(Level.WARNING, "Produto não encontrado com id: " + id);
			return Response.status(Response.Status.NOT_FOUND).entity(id).build();
		}
		return Response.ok().entity(produto).build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bueno.maribag.rest.BagResourceI#findGrupoById(java.lang.Long)
	 */
	@Override
	@GET
	@Path("/grupos/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response findGrupoById(@PathParam("id") Long id) {
		final Grupo grupo = grupoDao.find(id);
		if (grupo == null) {
			log.log(Level.WARNING, "Grupo não encontrado com id: " + id);
			return Response.status(Response.Status.NOT_FOUND).entity(id).build();
		}
		return Response.ok().entity(grupo).build();
	}

	@Override
	@POST
	@Path("/grupos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvaGrupo(Grupo grupo) {
		Response.ResponseBuilder builder = null;
		try {
			// valida produto
			validate(grupo);
			// insere o produto
			grupoDao.save(grupo);
			// Create an "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Handle bean validation issues
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			// Handle generic exceptions
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bueno.maribag.rest.BagResourceI#updateProduto(java.lang.Long,
	 * org.bueno.maribag.model.Produto)
	 */
	@Override
	@PUT
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/produtos/{id}")
	public Response updateProduto(@PathParam("id") Long id, Produto produto) {
		return salvaProduto(produto);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.bueno.maribag.rest.BagResourceI#salvaProduto(org.bueno.maribag.model.
	 * Produto)
	 */
	@Override
	@POST
	@Path("/produtos")
	@Produces(MediaType.APPLICATION_JSON)
	public Response salvaProduto(Produto produto) {
		Response.ResponseBuilder builder = null;
		try {
			// valida produto
			validate(produto);
			// salva o produto
			produtoDao.save(produto);
			// devolve "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Devolve os erros de validação
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			// Devolve erros genéricos
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.bueno.maribag.rest.BagResourceI#excluirProduto(java.lang.Long)
	 */
	@Override
	@DELETE
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/produtos/{id}")
	public Response excluirProduto(@PathParam("id") Long idProduto) {
		Response.ResponseBuilder builder = null;
		try {
			Produto produtoToDelete = produtoDao.find(idProduto);
			// excluir o produto
			produtoDao.delete(produtoToDelete);
			// devolve "ok" response
			builder = Response.ok();
		} catch (ConstraintViolationException ce) {
			// Devolve os erros de validação
			builder = createViolationResponse(ce.getConstraintViolations());
		} catch (Exception e) {
			// Devolve erros genéricos
			Map<String, String> responseObj = new HashMap<>();
			responseObj.put("error", e.getMessage());
			builder = Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
		}
		return builder.build();
	}

	// Valida um produto
	private void validate(Produto produto) {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<Produto>> violations = validator.validate(produto);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	// Valida um grupo
	private void validate(Grupo grupo) {
		// Create a bean validator and check for issues.
		Set<ConstraintViolation<Grupo>> violations = validator.validate(grupo);
		if (!violations.isEmpty()) {
			throw new ConstraintViolationException(new HashSet<ConstraintViolation<?>>(violations));
		}
	}

	// monta uma reposta de erro de validação
	private Response.ResponseBuilder createViolationResponse(Set<ConstraintViolation<?>> violations) {
		log.fine("Validação completada, total de violações encontradas: " + violations.size());

		Map<String, String> responseObj = new HashMap<>();

		for (ConstraintViolation<?> violation : violations) {
			responseObj.put(violation.getPropertyPath().toString(), violation.getMessage());
		}
		return Response.status(Response.Status.BAD_REQUEST).entity(responseObj);
	}
}