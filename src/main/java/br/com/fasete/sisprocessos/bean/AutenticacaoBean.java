package br.com.fasete.sisprocessos.bean;

import java.io.IOException;
import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.omnifaces.util.Faces;
import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Usuario;
import br.com.fasete.sisprocessos.dao.UsuarioDAO;

@ManagedBean
@SessionScoped()
public class AutenticacaoBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private Usuario usuarioLogado;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuarioLogado() {
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}

	@PostConstruct
	public void iniciar() {
		usuario = new Usuario();
		System.out.println();
	}

	public void autenticar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarioLogado = dao.autenticar(usuario.getLogin(), usuario.getSenha());
			
			if(usuarioLogado == null){
				Messages.addGlobalWarn("Login e/ou senha incorretos!");
				return;
			}else{
				
				if(usuarioLogado.isAtivo()){
					Faces.redirect("./pages/Principal.xhtml");
				}else{
					Messages.addGlobalWarn("Usuário desativado!");
					return;
				}
			}
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
	
	public boolean permissao(String tipoUsuario){
		if(usuarioLogado.getTipo().equals(tipoUsuario)){
			return true;
		}else{
			return false;
		}
	}
	
	public void deslogar(){
		try {
			Faces.invalidateSession();
			Faces.redirect("./pages/Autenticacao.xhtml");
		} catch (IOException e) {
			Messages.addGlobalError(e.getMessage());
		}
	}
}
