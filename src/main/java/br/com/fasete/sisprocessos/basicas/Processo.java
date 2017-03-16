package br.com.fasete.sisprocessos.basicas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Processo extends CodigoGenerico{

	private static final long serialVersionUID = 1L;

	@Column(length = 30, nullable = false)
	private String numero;
	
	@Temporal(TemporalType.DATE)
	private Date dtProcesso;
	
	@Temporal(TemporalType.DATE)
	private Date dtFinal;
	
	@Column(length = 30, nullable = false)
	private String situacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Tipo tipo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Contribuinte contribuinte;

	public Processo(){}
	
	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public Date getDtProcesso() {
		return dtProcesso;
	}

	public void setDtProcesso(Date dtProcesso) {
		this.dtProcesso = dtProcesso;
	}

	public Date getDtFinal() {
		return dtFinal;
	}

	public void setDtFinal(Date dtFinal) {
		this.dtFinal = dtFinal;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public Contribuinte getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}
	
}
