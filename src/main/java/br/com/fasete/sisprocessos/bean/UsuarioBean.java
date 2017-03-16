package br.com.fasete.sisprocessos.bean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.omnifaces.util.Messages;

import br.com.fasete.sisprocessos.basicas.Funcionario;
import br.com.fasete.sisprocessos.basicas.Usuario;
import br.com.fasete.sisprocessos.dao.FuncionarioDAO;
import br.com.fasete.sisprocessos.dao.UsuarioDAO;

@ManagedBean
@ViewScoped
public class UsuarioBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Usuario usuario;
	private List<Usuario> usuarios;
	private List<Funcionario> funcionarios;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	@PostConstruct
	public void listar() {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			usuarios = dao.listar();
		} catch (Exception e) {
			Messages.addGlobalInfo("Erro ao tentar listar os usuários!");
			e.printStackTrace();
		}
	}

	public void novo() {
		usuario = new Usuario();
		usuario.setAtivo(true);
		
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarios = dao.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os funcionários!");
			e.printStackTrace();
		}
	}

	public void salvar() {
		try {
			SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
			usuario.setSenha(hash.toHex());
			
			UsuarioDAO dao = new UsuarioDAO();
			dao.merge(usuario);
			usuarios = dao.listar();

			novo();
			Messages.addGlobalInfo("Usuário salvo com sucesso!");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar salvar o usuário!");
			e.printStackTrace();
		}
	}

	public void editar(Usuario usuarioSelecionado){
		usuario = usuarioSelecionado;
		
		try {
			FuncionarioDAO dao = new FuncionarioDAO();
			funcionarios = dao.listar("nome");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar listar os funcionários!");
			e.printStackTrace();
		}
	}

	public void excluir(Usuario usuarioSelecionado) {
		try {
			UsuarioDAO dao = new UsuarioDAO();
			dao.excluir(usuarioSelecionado);
			usuarios = dao.listar();

			Messages.addGlobalInfo("Usuário excluído com sucesso! ");
		} catch (RuntimeException e) {
			Messages.addGlobalError("Erro ao tentar excluir o usuário!");
			e.printStackTrace();
		}
	}
}
