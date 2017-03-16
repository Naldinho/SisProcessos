package br.com.fasete.sisprocessos.dao;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import br.com.fasete.sisprocessos.basicas.Processo;
import br.com.fasete.sisprocessos.util.HibernateUtil;

public class ProcessoDAO extends GenericDAO<Processo>{

	public Processo buscarPorNumero(String numero, String cpf){
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		
		try {
			Criteria consulta = sessao.createCriteria(Processo.class);
			consulta.createAlias("contribuinte", "c");
			consulta.add(Restrictions.eq("numero", numero));
			consulta.add(Restrictions.eq("c.cpf", cpf));
			Processo resultado = (Processo) consulta.uniqueResult();
			return resultado;
		} catch (RuntimeException e) {
			throw e;
		}finally {
			sessao.close();
		}
	}
}
