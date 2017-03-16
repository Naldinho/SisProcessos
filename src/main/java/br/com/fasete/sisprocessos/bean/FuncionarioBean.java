package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Funcionario;
import br.com.fasete.sisprocessos.basicas.Setor;
import br.com.fasete.sisprocessos.dao.FuncionarioDAO;
import br.com.fasete.sisprocessos.dao.SetorDAO;

@ManagedBean
@ViewScoped
public class FuncionarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Funcionario funcionario;
	private List<Funcionario> funcionarios;
	private List<Setor> setores;

	public Funcionario getFuncionario() {
		return funcionario;
	}

	public void setFuncionario(Funcionario funcionario) {
		this.funcionario = funcionario;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}
	
	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	@PostConstruct
	public void listar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarios = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os funcionários!");
			e.printStackTrace();
		}
	}

	public void novo() {
		funcionario = new Funcionario();
		
		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os setores!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.merge(funcionario);
			funcionarios = dao.listar();

			novo();
			Messages.addGlobalInfo("Funcionário salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o funcionário!");
			e.printStackTrace();
		}
	}

	public void editar(Funcionario funcionarioSelecionado) {
		funcionario = funcionarioSelecionado;
		
		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os setores!");
			e.printStackTrace();
		}
	}

	public void excluir(Funcionario funcionarioSelecionado) {
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			dao.excluir(funcionarioSelecionado);
			funcionarios = dao.listar();

			Messages.addGlobalInfo("Funcionário excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o funcionário!");
			e.printStackTrace();
		}
	}
}
