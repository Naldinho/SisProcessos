package br.com.fasete.sisprocessos.testes;

import org.hibernate.Session;

import br.com.fasete.sisprocessos.util.HibernateUtil;

public class testarHibernateUtil {
	public static void main(String[] args) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		sessao.close();
		HibernateUtil.getFabricaDeSessoes().close();
	}
}
