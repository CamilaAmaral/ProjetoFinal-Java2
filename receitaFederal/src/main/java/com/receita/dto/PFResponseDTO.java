package com.receita.dto;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.receita.dominio.PF;

public class PFResponseDTO {
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
	
	public static PFResponseDTO fromPF(PF pf) {
		
		PFResponseDTO dto = new PFResponseDTO();
		dto.setNome(pf.getNome());
		dto.setBairro(pf.getBairro());
		dto.setCidade(pf.getCidade());
		dto.setRua(pf.getRua());
		dto.setUf(pf.getUf());
		dto.setDtNascimento(pf.getDtNascimento());
		dto.setCpf(pf.getCpf());
		dto.setProfissao(pf.getProfissao());
		
		return dto;
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