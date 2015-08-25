package org.bueno.maribag.data.impl.test;

import java.math.BigDecimal;
import java.util.List;

import javax.ejb.EJB;

import org.bueno.maribag.data.impl.GrupoDaoImpl;
import org.bueno.maribag.data.impl.ProdutoDaoImpl;
import org.bueno.maribag.model.Grupo;
import org.bueno.maribag.model.Produto;
import org.eu.ingwar.tools.arquillian.extension.suite.annotations.ArquillianSuiteDeployment;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@RunWith(Arquillian.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ArquillianSuiteDeployment
public class ProdutoDaoImplTest {

	@Deployment
	public static Archive<?> createTestArchive() {
		return ShrinkWrap.create(WebArchive.class, "test.war").addPackages(true, "org.bueno.maribag")
				.addAsResource("META-INF/test-persistence.xml", "META-INF/persistence.xml")
				.addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml")
				// Deploy our test datasource
				.addAsWebInfResource("test-ds.xml");
	}

	@EJB
	private ProdutoDaoImpl produtoDao;
	@EJB
	private GrupoDaoImpl grupoDao;

	@Test
	public void testASaveGrupo() {
		Grupo grupo = new Grupo();
		grupo.setNome(Constants.PRIMEIRO_NOME_GRUPO);
		Assert.assertNull("Deve ser nulo o ID", grupo.getId());

		grupoDao.save(grupo);
		Assert.assertNotNull("Deve encontrar o grupo que inseriu.",
				grupoDao.findGrupoByNome(Constants.PRIMEIRO_NOME_GRUPO));
	}

	@Test
	public void testBSaveProduto() {
		Produto produto = new Produto();
		produto.setNome(Constants.PRIMEIRO_NOME_PRODUTO);
		produto.setQuantidade(BigDecimal.ONE);
		produto.setValor(BigDecimal.ONE);
		produto.setMarcado(Boolean.TRUE);
		Assert.assertNull("Id do produto deve ser nulo no persist.", produto.getId());

		Grupo grupo = grupoDao.findGrupoByNome(Constants.PRIMEIRO_NOME_GRUPO);
		Assert.assertNotNull(grupo);

		produto.setGrupo(grupo);
		produtoDao.save(produto);
		Assert.assertNotNull("Produto recuperado não pode ser nulo.",
				produtoDao.findProdutoByNome(Constants.PRIMEIRO_NOME_PRODUTO));
	}

	@Test
	public void testCEditProduto() {
		final String NOME_PRODUTO_ALTERNATIVO = "Produto2";

		Produto produto = produtoDao.findProdutoByNome(Constants.PRIMEIRO_NOME_PRODUTO);
		Assert.assertNotNull("Produto recuperado não pode ser nulo.", produto);

		produto.setNome(NOME_PRODUTO_ALTERNATIVO);
		produto.setQuantidade(BigDecimal.valueOf(2.0));
		produto.setValor(BigDecimal.valueOf(2.0));
		produto.setMarcado(Boolean.FALSE);
		produtoDao.save(produto);

		Produto produtoAlterado = produtoDao.findProdutoByNome(NOME_PRODUTO_ALTERNATIVO);
		Assert.assertNotNull(produtoAlterado);
		Assert.assertNotEquals("Parametros devem ser diferentes", produtoAlterado.getNome(),
				Constants.PRIMEIRO_NOME_PRODUTO);
		Assert.assertTrue("Parametros devem ser diferentes",
				produtoAlterado.getQuantidade().compareTo(BigDecimal.ONE) != 0);
		Assert.assertTrue("Parametros devem ser diferentes", produtoAlterado.getValor().compareTo(BigDecimal.ONE) != 0);
		Assert.assertNotEquals("Parametros devem ser diferentes", produtoAlterado.getMarcado(), Boolean.TRUE);

		produto = produtoDao.findProdutoByNome(NOME_PRODUTO_ALTERNATIVO);
		Assert.assertNotNull("Produto recuperado não pode ser nulo.", produto);

		produto.setNome(Constants.PRIMEIRO_NOME_PRODUTO);
		produtoDao.save(produto);
	}

	@Test
	public void testCFindProdutoByNome() {
		Assert.assertNotNull("Produto recuperado não pode ser nulo.",
				produtoDao.findProdutoByNome(Constants.PRIMEIRO_NOME_PRODUTO));
	}

	@Test
	public void testCFind() {
		Produto produto = produtoDao.findProdutoByNome(Constants.PRIMEIRO_NOME_PRODUTO);
		Assert.assertNotNull("Produto recuperado não pode ser nulo.", produtoDao.find(produto.getId()));
	}

	@Test
	public void testCFindAll() {
		List<Produto> produtos = produtoDao.findAll();
		Assert.assertNotNull("Lista de produtos não pode ser nula.", produtos);

		Assert.assertTrue(produtos.size() > 0);
	}

	@Test
	public void testZDelete() {
		Produto produto = produtoDao.findProdutoByNome(Constants.PRIMEIRO_NOME_PRODUTO);
		Assert.assertNotNull("Produto não pode ser nulo.", produto);

		produtoDao.delete(produto);
		Assert.assertNull("Deve apagar o produto, assim retorna nulo.", produtoDao.find(produto.getId()));
	}

}
