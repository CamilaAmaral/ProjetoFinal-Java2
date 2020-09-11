package com.receita.dominio;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PJ implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Size(min = 14, max = 14)
	@Column(unique = true)
	@NotNull
	private String cnpj;
	
	@NotNull
	private String nome;
	
	@ManyToMany
	@JoinTable (
				name = "pj_pf",
				joinColumns = @JoinColumn(name = "pj_id", referencedColumnName =  "id"),
				inverseJoinColumns = @JoinColumn(name = "pf_id", referencedColumnName = "id")
				)
	private Set <PF> socios = new HashSet<>();
	
	@NotNull
	private String dtInicial;
	
	@NotNull
	private String cdAtividade;
	
	@NotNull
	private String atividade;
	
	@NotNull
	private String rua;
	
	@NotNull
	private String bairro;
	
	@NotNull
	private String cidade;
	
	@NotNull
	@Size(min = 2, max = 2)
	private String uf;
	
	public PJ() {
	}
	
	public PJ(@Size(min = 14, max = 14) @NotNull String cnpj, String nome, Set<PF> socios,
			@NotNull String dtInicial, @NotNull String cdAtividade, @NotNull String atividade, @NotNull String rua,
			@NotNull String bairro, @NotNull String cidade, @NotNull @Size(min = 2, max = 2) String uf) {
		super();
		this.cnpj = cnpj;
		this.nome = nome;
		this.socios = socios;
		this.dtInicial = dtInicial;
		this.cdAtividade = cdAtividade;
		this.atividade = atividade;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public PJ(String cpf) {
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtInicial() {
		return dtInicial;
	}

	public void setDtInicial(String dtInicial) {
		this.dtInicial = dtInicial;
	}

	public String getCdAtividade() {
		return cdAtividade;
	}

	public void setCdAtividade(String cdAtividade) {
		this.cdAtividade = cdAtividade;
	}

	public String getAtividade() {
		return atividade;
	}

	public void setAtividade(String atividade) {
		this.atividade = atividade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public Integer getId() {
		return id;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public Set<PF> getSocios() {
		return socios;
	}

	public void setSocios(Set<PF> socios) {
		this.socios = socios;
	}
}