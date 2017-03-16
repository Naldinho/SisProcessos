package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.GenericJDBCException;
import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Encaminhamento;
import br.com.fasete.sisprocessos.basicas.Funcionario;
import br.com.fasete.sisprocessos.basicas.Processo;
import br.com.fasete.sisprocessos.basicas.Setor;
import br.com.fasete.sisprocessos.dao.EncaminhamentoDAO;
import br.com.fasete.sisprocessos.dao.FuncionarioDAO;
import br.com.fasete.sisprocessos.dao.ProcessoDAO;
import br.com.fasete.sisprocessos.dao.SetorDAO;

@ManagedBean
@ViewScoped
public class EncaminhamentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Encaminhamento encaminhamento;
	private Processo processo;
	private List<Encaminhamento> encaminhamentos;
	private List<Processo> processos;
	private List<Setor> setores;
	private List<Funcionario> responsaveis;

	public Encaminhamento getEncaminhamento() {
		return encaminhamento;
	}

	public void setEncaminhamento(Encaminhamento encaminhamento) {
		this.encaminhamento = encaminhamento;
	}

	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}

	public List<Setor> getSetores() {
		return setores;
	}

	public void setSetores(List<Setor> setores) {
		this.setores = setores;
	}

	public List<Funcionario> getResponsaveis() {
		return responsaveis;
	}

	public void setResponsaveis(List<Funcionario> responsaveis) {
		this.responsaveis = responsaveis;
	}

	@PostConstruct
	public void listar() {
		try {
			EncaminhamentoDAO dao = new EncaminhamentoDAO();
			encaminhamentos = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os Encaminhametos!");
			e.printStackTrace();
		}
	}

	public void novo() {
		encaminhamento = new Encaminhamento();

		try {
			ProcessoDAO dao = new ProcessoDAO();
			processos = dao.listar("numero");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os processos!");
			e.printStackTrace();
		}

		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os setores!");
			e.printStackTrace();
		}

		responsaveis = new ArrayList<Funcionario>();
	}

	public void encaminhar(Processo processoSelecionado) {
		encaminhamento = new Encaminhamento();
		processo = processoSelecionado;
		encaminhamento.setProcesso(processo);

		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os setores!");
			e.printStackTrace();
		}

		responsaveis = new ArrayList<Funcionario>();
	}

	public void salvar() {

		try {
			EncaminhamentoDAO dao = new EncaminhamentoDAO();
			dao.merge(encaminhamento);
			encaminhamentos = dao.listar();

			novo();
			Messages.addGlobalInfo("Encaminhamento salvo com sucesso!");
		} catch (GenericJDBCException e) {
			Messages.addGlobalError("A data do Encaminhamento inserida é memhor que a do ultimo emcaminhamneto!");
			e.printStackTrace();
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o encaminhamento!");
			e.printStackTrace();
		}
	}

	public void editar(Encaminhamento EncaminhamentoSelecionado) {
		encaminhamento = EncaminhamentoSelecionado;

		try {
			ProcessoDAO dao = new ProcessoDAO();
			processos = dao.listar("numero");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os processos!");
			e.printStackTrace();
		}

		try {
			SetorDAO dao = new SetorDAO();
			setores = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os setores!");
			e.printStackTrace();
		}
	}

	public void excluir(Encaminhamento EncaminhamentoSelecionado) {
		try {
			EncaminhamentoDAO dao = new EncaminhamentoDAO();
			dao.excluir(EncaminhamentoSelecionado);
			encaminhamentos = dao.listar();

			Messages.addGlobalInfo("Encaminhamento excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o encaminhamento!");
			e.printStackTrace();
		}
	}

	public void comboDependente() {
		try {
			if (encaminhamento.getSetor() != null) {
				FuncionarioDAO dao = new FuncionarioDAO();
				responsaveis = dao.listarFuncionariosPorSetor(encaminhamento.getSetor().getCodigo());
			} else {
				System.out.println("vazio");
				responsaveis = new ArrayList<>();
			}
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os responsáveis!");
			e.printStackTrace();
		}

	}

	public void visualizarEncaminhamentos(Processo processoSelecionado) {
		processo = processoSelecionado;

		try {
			EncaminhamentoDAO dao = new EncaminhamentoDAO();
			encaminhamentos = dao.listarEncaminhamentos(processo.getCodigo());

		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar visualizar os encaminhamentos!");
			e.printStackTrace();
		}
	}
}
