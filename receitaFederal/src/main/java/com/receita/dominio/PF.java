package com.receita.dominio;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class PF implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Size(min = 11, max = 11)
	@Column(unique = true)
	@NotNull
	private String cpf;

	@NotNull
	private String nome;

	@NotNull
	private String dtNascimento;

	@NotNull
	private String profissao;

	@NotNull
	private String rua;

	@NotNull
	private String bairro;

	@NotNull
	private String cidade;

	@Size(min = 2, max = 2)
	@NotNull
	private String uf;

	public PF() {
	}

	public PF(@Size(min = 11, max = 11) @NotNull String cpf, String nome, String dtNascimento, String profissao,
			String rua, String bairro, String cidade, @Size(min = 2, max = 2) String uf) {
		super();
		this.cpf = cpf;
		this.nome = nome;
		this.dtNascimento = dtNascimento;
		this.profissao = profissao;
		this.rua = rua;
		this.bairro = bairro;
		this.cidade = cidade;
		this.uf = uf;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(String dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public String getProfissao() {
		return profissao;
	}

	public void setProfissao(String profissao) {
		this.profissao = profissao;
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

	public Integer getId() {
		return id;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}
}