package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Tipo;
import br.com.fasete.sisprocessos.dao.TipoDAO;

@ManagedBean
@ViewScoped
public class TipoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Tipo tipo;
	private List<Tipo> tipos;

	public Tipo getTipo() {
		return tipo;
	}

	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}

	public List<Tipo> getTipos() {
		return tipos;
	}

	public void setTipos(List<Tipo> tipos) {
		this.tipos = tipos;
	}

	@PostConstruct
	public void listar() {
		try {
			TipoDAO dao = new TipoDAO();
			tipos = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os tipos!");
			e.printStackTrace();
		}
	}

	public void novo() {
		tipo = new Tipo();
	}

	public void salvar() {
		try {
			TipoDAO dao = new TipoDAO();
			dao.merge(tipo);
			tipos = dao.listar();

			novo();
			Messages.addGlobalInfo("Tipo salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o tipo!");
			e.printStackTrace();
		} 
	}

	public void editar(Tipo tipoSelecionado){
		tipo = tipoSelecionado;
	}

	public void excluir(Tipo tipoSelecionado) {
		try {
			TipoDAO dao = new TipoDAO();
			dao.excluir(tipoSelecionado);
			tipos = dao.listar();

			Messages.addGlobalInfo("Tipo excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o tipo!");
			e.printStackTrace();
		}
	}
}
