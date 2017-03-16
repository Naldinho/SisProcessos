package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Encaminhamento;
import br.com.fasete.sisprocessos.basicas.Processo;
import br.com.fasete.sisprocessos.dao.EncaminhamentoDAO;
import br.com.fasete.sisprocessos.dao.ProcessoDAO;

@ManagedBean
@ViewScoped
public class BuscarProcessoBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private Processo processo;
	private String cpf;
	private List<Encaminhamento> encaminhamentos;
	private boolean exibirPainelHistorico;
	
	public Processo getProcesso() {
		return processo;
	}

	public void setProcesso(Processo processo) {
		this.processo = processo;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public List<Encaminhamento> getEncaminhamentos() {
		return encaminhamentos;
	}

	public void setEncaminhamentos(List<Encaminhamento> encaminhamentos) {
		this.encaminhamentos = encaminhamentos;
	}

	public boolean isExibirPainelHistorico() {
		return exibirPainelHistorico;
	}

	public void setExibirPainelHistorico(boolean exibirPainelHistorico) {
		this.exibirPainelHistorico = exibirPainelHistorico;
	}

	@PostConstruct
	private void iniciar(){
		processo = new Processo();
	}
	
	public void buscarProcesso(){
		try {
			ProcessoDAO processoDao = new ProcessoDAO();
			Processo resultado = processoDao.buscarPorNumero(processo.getNumero(), cpf);
			
			if(resultado == null){
				exibirPainelHistorico = false;
				Messages.addGlobalWarn("Não existe processo cadastrado com o numero ou cpf informado!");
			}else{
				exibirPainelHistorico = true;
				processo = resultado;
				
				EncaminhamentoDAO dao = new EncaminhamentoDAO();
				encaminhamentos = dao.listarEncaminhamentos(processo.getCodigo());
			}
					
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao consultar o processo!");
			e.printStackTrace();
		}
	}
}
