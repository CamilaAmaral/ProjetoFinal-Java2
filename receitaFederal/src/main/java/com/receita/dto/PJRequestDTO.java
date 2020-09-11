package com.receita.dto;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

import com.receita.dominio.PF;
import com.receita.dominio.PJ;
import com.receita.repositorio.PfRepository;

public class PJRequestDTO {

	@Size(min = 14, max = 14)
	@Column(unique = true)
	@NotNull
	private String cnpj;

	private String nome;

	@NotNull
	private String dtInicial;
	
	@NotNull
	private Set<String> cpfSocios;

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

	public PJ toPJ(PfRepository pfRepository) {

		Set <PF> socios = new HashSet<>();
		
		for (String cpf : this.cpfSocios) {

			PF pf = pfRepository.findByCpf(cpf);

			if (pf == null) {
				throw new HttpServerErrorException(HttpStatus.BAD_REQUEST, "O sócio não existe");
			}
			socios.add(pf);

		}
		PJ pj = new PJ(this.cnpj, this.nome,socios,
				 this.dtInicial,  this.cdAtividade,  this.atividade,  this.rua,
				 this.bairro,  this.cidade, this.uf);

		return pj;
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

	public Set<String> getCpfSocios() {
		return cpfSocios;
	}

	public void setCpfSocios(Set<String> cpfSocios) {
		this.cpfSocios = cpfSocios;
	}
}