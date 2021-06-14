package com.teste.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table
public class Pedido {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String beneficiario;
	private BigDecimal valorPedido;
	private Boolean aprovadoSelecionado;
	private Boolean eleicaoSelecionado;
	private String nomeFantasia;
	private String razaoSocial;
	private int qtdParcelas;
	private BigDecimal total;
	private Boolean aprovacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBeneficiario() {
		return beneficiario;
	}

	public void setBeneficiario(String beneficiario) {
		this.beneficiario = beneficiario;
	}

	public BigDecimal getValorPedido() {
		return valorPedido;
	}

	public void setValorPedido(BigDecimal valorPedido) {
		this.valorPedido = valorPedido;
	}

	public Boolean getAprovadoSelecionado() {
		return aprovadoSelecionado;
	}

	public void setAprovadoSelecionado(Boolean aprovadoSelecionado) {
		this.aprovadoSelecionado = aprovadoSelecionado;
	}

	public Boolean getEleicaoSelecionado() {
		return eleicaoSelecionado;
	}

	public void setEleicaoSelecionado(Boolean eleicaoSelecionado) {
		this.eleicaoSelecionado = eleicaoSelecionado;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public int getQtdParcelas() {
		return qtdParcelas;
	}

	public void setQtdParcelas(int qtdParcelas) {
		this.qtdParcelas = qtdParcelas;
	}

	public BigDecimal getTotal() {
		return total;
	}

	public void setTotal(BigDecimal total) {
		this.total = total;
	}

	public Boolean getAprovacao() {
		return aprovacao;
	}

	public void setAprovacao(Boolean aprovacao) {
		this.aprovacao = aprovacao;
	}

}
