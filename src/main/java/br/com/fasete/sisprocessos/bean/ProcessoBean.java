package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Contribuinte;
import br.com.fasete.sisprocessos.basicas.Processo;
import br.com.fasete.sisprocessos.basicas.Tipo;
import br.com.fasete.sisprocessos.dao.ContribuinteDAO;
import br.com.fasete.sisprocessos.dao.ProcessoDAO;
import br.com.fasete.sisprocessos.dao.TipoDAO;

@ManagedBean
@ViewScoped
public class ProcessoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Processo processo;
	private List<Processo> processos;
	private List<Contribuinte> contribuintes;
	private List<Tipo> tipos;

	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public List<Processo> getProcessos() {
		return processos;
	}

	public void setProcessos(List<Processo> processos) {
		this.processos = processos;
	}
	
	public List<Contribuinte> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
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
			ProcessoDAO dao = new ProcessoDAO();
			processos = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os processos!");
			e.printStackTrace();
		}
	}

	public void novo() {
		processo = new Processo();
		
		try {
			ContribuinteDAO dao = new ContribuinteDAO();
			contribuintes = dao.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os contribuintes!");
			e.printStackTrace();
		}
		
		try {
			TipoDAO dao = new TipoDAO();
			tipos = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os tipos de processo!");
			e.printStackTrace();
		}
		
	}

	public void salvar() {
		try {
			ProcessoDAO dao = new ProcessoDAO();
			dao.merge(processo);
			processos = dao.listar();

			novo();
			Messages.addGlobalInfo("Processo salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o processo!");
			e.printStackTrace();
		}
	}

	public void editar(Processo processoSelecionado) {
		processo = processoSelecionado;
		
		try {
			ContribuinteDAO dao = new ContribuinteDAO();
			contribuintes = dao.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os contribuintes!");
			e.printStackTrace();
		}
		
		try {
			TipoDAO dao = new TipoDAO();
			tipos = dao.listar("descricao");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os tipos de processo!");
			e.printStackTrace();
		}
	}

	public void excluir(Processo processoSelecionado) {
		try {
			ProcessoDAO dao = new ProcessoDAO();
			dao.excluir(processoSelecionado);
			processos = dao.listar();

			Messages.addGlobalInfo("Processo excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o processo!");
			e.printStackTrace();
		}
	}
}
