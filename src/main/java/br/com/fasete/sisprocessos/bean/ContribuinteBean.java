package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.hibernate.exception.GenericJDBCException;
import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Contribuinte;
import br.com.fasete.sisprocessos.dao.ContribuinteDAO;

@ManagedBean
@ViewScoped
public class ContribuinteBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String[] estados = {"AC","AL","AM","AP","BA","CE","DF","ES","GO","MA","MG","MS","MT",
            "PA","PB","PE","PI","PR","RJ","RN","RO","RR","RS","SC","SE","SP","TO"};
	
	private Contribuinte contribuinte;
	private List<Contribuinte> contribuintes;
	
	public String[] getEstados() {
		return estados;
	}

	public void setEstados(String[] estados) {
		this.estados = estados;
	}

	public Contribuinte getContribuinte() {
		return contribuinte;
	}

	public void setContribuinte(Contribuinte contribuinte) {
		this.contribuinte = contribuinte;
	}

	public List<Contribuinte> getContribuintes() {
		return contribuintes;
	}

	public void setContribuintes(List<Contribuinte> contribuintes) {
		this.contribuintes = contribuintes;
	}
	
	@PostConstruct
	public void listar() {
		try {
			ContribuinteDAO dao = new ContribuinteDAO();
			contribuintes = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os contribuintes!");
			e.printStackTrace();
		}
	}

	public void novo() {
		contribuinte = new Contribuinte();
	}

	public void salvar() {
		try {
			ContribuinteDAO dao = new ContribuinteDAO();
			dao.merge(contribuinte);
			
			novo();
			Messages.addGlobalInfo("contribuinte salvo com sucesso!");
		} catch (GenericJDBCException e) {
			Messages.addGlobalError("CPF já cadastrado!");
			e.printStackTrace();
		} catch (RuntimeException e2) {
			Messages.addGlobalError("Erro ao tentar salvar o contribuinte!");
			e2.printStackTrace();
		} finally {
			ContribuinteDAO dao = new ContribuinteDAO();
			contribuintes = dao.listar();
		}
	}

	public void editar(Contribuinte contribuinteSelecionado){
		contribuinte = contribuinteSelecionado;
	}

	public void excluir(Contribuinte contribuinteSelecionado) {
		try {
			ContribuinteDAO dao = new ContribuinteDAO();
			dao.excluir(contribuinteSelecionado);
			contribuintes = dao.listar();

			Messages.addGlobalInfo("contribuinte excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o contribuinte!");
			e.printStackTrace();
		} 
	}
}
