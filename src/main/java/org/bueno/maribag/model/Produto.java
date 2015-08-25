package org.bueno.maribag.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.Valid;
import javax.validation.constraints.DecimalMin;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;

import org.eclipse.persistence.oxm.annotations.XmlInverseReference;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * 
 * @author Bueno
 *
 *         Classe representante de um produto de compras
 *
 */
@SuppressWarnings("serial")
@Entity
@Table(name = "produto")
@XmlRootElement
public class Produto implements Serializable {

	@Id
	@SequenceGenerator(name = "produtoSeq", sequenceName = "idproduto_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtoSeq")
	@Column(name = "idproduto", nullable = false, updatable = false)
	private Long id;
	@Column(name = "marcado")
	private Boolean marcado = Boolean.FALSE;
	@Column(name = "nome", length = 100, nullable = false)
	@NotEmpty(message = "Nome do produto não pode ser vazio")
	private String nome;
	@Column(name = "quantidade", precision = 15, scale = 3)
	@DecimalMin(value = "0.001", message = "Quantidade deve ser maior que {value}.")
	private BigDecimal quantidade = BigDecimal.ONE;
	@Column(name = "valor", precision = 15, scale = 2)
	@DecimalMin(value = "0.01", message = "Preço deve ser maior que {value}.")
	private BigDecimal valor = BigDecimal.ZERO;
	@ManyToOne
	@JoinColumn(name = "id_grupo")
	@Valid
	private Grupo grupo;

	public Produto() {
	}

	public void change() {
		this.marcado = this.marcado ? Boolean.FALSE : Boolean.TRUE;
	}

	@XmlID
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public Boolean getMarcado() {
		return marcado;
	}

	public void setMarcado(Boolean marcado) {
		this.marcado = marcado;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	@XmlElement
	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	@XmlElement
	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	@XmlInverseReference(mappedBy = "produtos")
	public Grupo getGrupo() {
		return grupo;
	}

	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}

}
