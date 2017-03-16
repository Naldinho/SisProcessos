package br.com.fasete.sisprocessos.dao;

import java.util.List;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.junit.Ignore;
import org.junit.Test;

import br.com.fasete.sisprocessos.basicas.Usuario;

public class UsuarioDAOTest {
	@Test
	public void salvar() {
		Usuario usuario = new Usuario("admin", "123", "ADMIN");
		
		usuario.setSenhaSemCriptografia("123");

		//criptografar senha md5.
		SimpleHash hash = new SimpleHash("md5", usuario.getSenhaSemCriptografia());
		usuario.setSenha(hash.toHex());
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.salvar(usuario);
	}

	@Test
	@Ignore
	public void listar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		List<Usuario> resultado = usuarioDAO.listar();

		System.out.println("Total de registro: " + resultado.size());
		
		for (Usuario usuario : resultado) {
			System.out.println(usuario.getCodigo() + " - " + usuario.getLogin());
		}
	}
	
	@Test
	@Ignore
	public void buscar() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(5);

		if(usuario == null){
			System.out.println("Nenhum resultado encontrado!");
		}else{
			System.out.println("Login: " + usuario.getLogin());
		}
	}
	
	@Test
	@Ignore
	public void excluir(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(4);
		
		if(usuario == null){
			System.out.println("Nenhum resultado encontrado!");
		}else{
			usuarioDAO.excluir(usuario);
			System.out.println("Excluido com sucesso!");
		}
	}
	
	@Test
	@Ignore
	public void editar(){
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(1);
		
		if(usuario == null){
			System.out.println("Nenhum resultado encontrado!");
		}else{
			usuario.setTipo("func");
			
			usuarioDAO.editar(usuario);
			System.out.println("Editado com sucesso!");
		}
	}
	
	@Test
	@Ignore
	public void merge() {
		/*//salvar
		Usuario usuario = new Usuario("mairla", "123", "admin");

		UsuarioDAO usuarioDAO = new UsuarioDAO();
		usuarioDAO.merge(usuario);*/
		
		//editar
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.buscar(2);
		
		usuario.setTipo("func");
		usuarioDAO.merge(usuario);
	}
	
	@Test
	@Ignore
	public void autenticar(){
		String login = "casper";
		String senha = "123";
		
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		Usuario usuario = usuarioDAO.autenticar(login, senha);
		
		System.out.println("Usuario Autenticado: " + usuario.getLogin());
	}
}
