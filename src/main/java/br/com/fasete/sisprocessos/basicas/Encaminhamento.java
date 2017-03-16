package br.com.fasete.sisprocessos.basicas;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Encaminhamento extends CodigoGenerico{

	private static final long serialVersionUID = 1L;

	@Temporal(TemporalType.DATE)
	private Date dtEncaminhamento;
	
	private String observacao;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Processo processo;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Setor setor;
	
	@ManyToOne
	@JoinColumn(nullable = false)
	private Funcionario responsavel;

	public Date getDtEncaminhamento() {
		return dtEncaminhamento;
	}

	public void setDtEncaminhamento(Date dtEncaminhamento) {
		this.dtEncaminhamento = dtEncaminhamento;
	}

	public String getObservacao() {
		return observacao;
	}

	public void setObservacao(String observacao) {
		this.observacao = observacao;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public Funcionario getResponsavel() {
		return responsavel;
	}

	public void setResponsavel(Funcionario responsavel) {
		this.responsavel = responsavel;
	}
}
