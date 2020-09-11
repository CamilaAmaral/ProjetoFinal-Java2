package com.receita.dto;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.receita.dominio.PF;
import com.receita.dominio.PJ;

public class PJResponseDTO {
	
	@Size(min = 14, max = 14)
	@Column(unique = true)
	@NotNull
	private String cnpj;

	@NotNull
	private String nome;

	@NotNull
	private String dtInicial;

	@NotNull
	private List<String> socios;

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

	public static PJResponseDTO fromPJ(PJ pj) {
		
		PJResponseDTO dto = new PJResponseDTO();
		dto.setCnpj(pj.getCnpj());
		dto.setNome(pj.getNome());
		dto.setAtividade(pj.getAtividade());
		dto.setBairro(pj.getBairro());
		dto.setCdAtividade(pj.getCdAtividade());
		dto.setCidade(pj.getCidade());
		dto.setDtInicial(pj.getDtInicial());
		dto.setRua(pj.getRua());
		dto.setUf(pj.getUf());
		
		dto.socios = new ArrayList<>();
		
		for (PF a : pj.getSocios()) {
			dto.getSocios().add(a.getNome());
		}
		return dto;
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

	public void setUf(String uf) {
		this.uf = uf;
	}

	public List<String> getSocios() {
		return socios;
	}

	public void setSocios(List<String> cpfSocios) {
		this.socios = cpfSocios;
	}
}