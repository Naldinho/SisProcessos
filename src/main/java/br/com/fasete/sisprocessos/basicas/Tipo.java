package br.com.fasete.sisprocessos.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity
public class Tipo extends CodigoGenerico{

	private static final long serialVersionUID = 1L;
	
	@Column(length = 50, nullable = false)
	private String descricao;
	
	public Tipo(){}

	public Tipo(String descricao) {
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
