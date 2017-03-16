package br.com.fasete.sisprocessos.dao;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.fasete.sisprocessos.basicas.Usuario;
import br.com.fasete.sisprocessos.util.HibernateUtil;

public class UsuarioDAO extends GenericDAO<Usuario> {

	public Usuario autenticar(String login, String senha) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();

		try {
			Criteria consulta = sessao.createCriteria(Usuario.class);
			consulta.add(Restrictions.eq("login", login));
			
			//verificar senha critografada.
			SimpleHash hash = new SimpleHash("md5", senha);
			consulta.add(Restrictions.eq("senha", hash.toHex()));
			
			Usuario resultado = (Usuario) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		} finally {
			sessao.close();
		}
	}
}
