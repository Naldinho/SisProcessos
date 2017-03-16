package br.com.fasete.sisprocessos.basicas;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class Usuario extends CodigoGenerico {

	private static final long serialVersionUID = 1L;

	@Column(length = 50, nullable = false)
	private String login;

	@Transient
	private String senhaSemCriptografia;
	
	@Column(length = 32, nullable = false)
	private String senha;

	@Column(length = 50, nullable = false)
	private String tipo;

	private boolean ativo;

	@OneToOne
	private Funcionario funcionario;
	
	public Usuario(){}

	public Usuario(String login, String senha, String tipo) {
		this.login = login;
		this.senha = senha;
		this.tipo = tipo;
		this.ativo = true;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenhaSemCriptografia() {
		return senhaSemCriptografia;
	}

	public void setSenhaSemCriptografia(String senhaSemCriptografia) {
		this.senhaSemCriptografia = senhaSemCriptografia;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	@Transient
	public String getTipoFormatado() {
		String tipoFormatado = null;

		if (tipo.equals("ADMIN")) {
			tipoFormatado = "ADMINISTRADOR";
		} else if (tipo.equals("FUNC")) {
			tipoFormatado = "FUNCIONÁRIO";
		}

		return tipoFormatado;
	}

	@Transient
	public String getAtivoFormatado() {
		String ativoFormatado = null;

		if (ativo) {
			ativoFormatado = "SIM";
		} else {
			ativoFormatado = "NÃO";
		}

		return ativoFormatado;
	}
}
