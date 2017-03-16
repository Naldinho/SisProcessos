package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Setor;
import br.com.fasete.sisprocessos.dao.SetorDAO;

@ManagedBean
@ViewScoped
public class SetorBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Setor setor;
	private List<Setor> setores;

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setors) {
		this.setores = setors;
	}

	@PostConstruct
	public void listar() {
		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os setors!");
			e.printStackTrace();
		}
	}

	public void novo() {
		setor = new Setor();
	}

	public void salvar() {
		try {
			SetorDAO dao = new SetorDAO();
			dao.merge(setor);
			setores = dao.listar();

			novo();
			Messages.addGlobalInfo("setor salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o setor!");
			e.printStackTrace();
		} 
	}

	public void editar(Setor setorSelecionado){
		setor = setorSelecionado;
	}

	public void excluir(Setor setorSelecionado) {
		try {
			SetorDAO dao = new SetorDAO();
			dao.excluir(setorSelecionado);
			setores = dao.listar();

			Messages.addGlobalInfo("setor excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o setor!");
			e.printStackTrace();
		}
	}
}
