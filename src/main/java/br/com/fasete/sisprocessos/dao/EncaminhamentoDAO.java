package br.com.fasete.sisprocessos.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.fasete.sisprocessos.basicas.Encaminhamento;
import br.com.fasete.sisprocessos.util.HibernateUtil;

public class EncaminhamentoDAO extends GenericDAO<Encaminhamento>{

	@SuppressWarnings("unchecked")
	public List<Encaminhamento> listarEncaminhamentos(Long codigoProcesso) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Encaminhamento.class);
			consulta.add(Restrictions.eq("processo.codigo", codigoProcesso));
			consulta.addOrder(Order.asc("dtEncaminhamento"));
			List<Encaminhamento> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
	
}
