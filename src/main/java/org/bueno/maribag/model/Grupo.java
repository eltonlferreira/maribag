package org.bueno.maribag.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import org.hibernate.validator.constraints.NotEmpty;

@SuppressWarnings("serial")
@Entity
@Table(name = "grupo")
@XmlRootElement
public class Grupo implements Serializable {

	@Id
	@SequenceGenerator(name = "idgrupo_seq", sequenceName = "idgrupo_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "idgrupo_seq")
	@Column(name = "idgrupo", nullable = false, updatable = false)
	private Long id;
	@Column(name = "nome", length = 100, nullable = false)
	@NotEmpty(message = "Nome do grupo não pode ser vazio")
	private String nome;
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "grupo")
	@OrderBy("nome")
	private List<Produto> produtos = new ArrayList<>();;

	public Grupo() {

	}

	public void addProduto(Produto produto) {
		produtos.add(produto);
		produto.setGrupo(this);
	}

	public void removeProduto(Produto produto) {
		produtos.remove(produto);
		produto.setGrupo(null);
	}

	@XmlID
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@XmlElement
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	// @XmlElementWrapper(name = "produtos")
	// @XmlElement(name = "produto", type = Produto.class)
	@XmlTransient
	public List<Produto> getProdutos() {
		return produtos;
	}

}
