package br.com.fasete.sisprocessos.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Setor extends CodigoGenerico{

	private static final long serialVersionUID = 1L;

	@Column(length = 80, nullable = false)
	private String descricao;

	public Setor(){}
	
	public Setor(String descricao) {
		super();
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
