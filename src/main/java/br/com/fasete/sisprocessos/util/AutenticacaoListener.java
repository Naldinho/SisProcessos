package br.com.fasete.sisprocessos.util;

import java.io.IOException;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Usuario;
import br.com.fasete.sisprocessos.bean.AutenticacaoBean;

public class AutenticacaoListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		String paginaAtual = Faces.getViewId();

		// paginas publicas.
		boolean paginaAutenticacao = paginaAtual.contains("Autenticacao.xhtml");
		boolean paginaBuscarProcesso = paginaAtual.contains("BuscarProcesso.xhtml");

		// verifica se é a pagina autenticacao. (pagina publica)
		// se nao for redireciona para a pagina de autenticacao.
		if (!paginaAutenticacao && !paginaBuscarProcesso) {
			AutenticacaoBean autenticacaoBean = Faces.getSessionAttribute("autenticacaoBean");

			try {
				if (autenticacaoBean == null) {
					Faces.redirect("./pages/Autenticacao.xhtml");
					return;
				}

				Usuario usuario = autenticacaoBean.getUsuarioLogado();
				if (usuario == null) {
					Faces.redirect("./pages/Autenticacao.xhtml");
					return;
				}
			} catch (IOException e) {
				Messages.addGlobalError(e.getMessage());
			}
		}
	}

	@Override
	public void beforePhase(PhaseEvent event) {

	}

	@Override
	public PhaseId getPhaseId() {
		return PhaseId.RESTORE_VIEW;
	}

}
