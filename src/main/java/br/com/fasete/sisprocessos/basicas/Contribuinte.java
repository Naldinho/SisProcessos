package br.com.fasete.sisprocessos.basicas;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Contribuinte extends CodigoGenerico {

	private static final long serialVersionUID = 1L;
	
	@Column(length = 80, nullable = false)
	private String nome;

	@Column(length = 20, nullable = false)
	private String cpf;

	@Column(nullable = false)
	private char sexo;

	@Column(nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dtNasc;

	@Column(length = 15)
	private String tel;
	
	@Column(length = 15)
	private String cel;

	@Column(length = 40, nullable = false)
	private String bairro;

	@Column(length = 40, nullable = false)
	private String cidade;

	@Column(length = 2, nullable = false)
	private String uf;

	@Temporal(TemporalType.DATE)
	private Date dtCadastro;

	public Contribuinte() {
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getDtNasc() {
		return dtNasc;
	}

	public void setDtNasc(Date dtNasc) {
		this.dtNasc = dtNasc;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}

	public String getCel() {
		return cel;
	}

	public void setCel(String cel) {
		this.cel = cel;
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

	public Date getDtCadastro() {
		return dtCadastro;
	}

	public void setDtCadastro(Date dtCadastro) {
		this.dtCadastro = dtCadastro;
	}

	/*@Transient
	public void setDataFormatada(String dtNasc) {
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		try {
			this.dtNasc = formato.parse(dtNasc);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	@Transient
	public String getDataFormatada() {
		String dataFormatada = null;
		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

		if (dtNasc != null) {
			dataFormatada = formato.format(dtNasc);
		}
		dataFormatada = formato.format(dtNasc);

		return dataFormatada;
	}*/

}
