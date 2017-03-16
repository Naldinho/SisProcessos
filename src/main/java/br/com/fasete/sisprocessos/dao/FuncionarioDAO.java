package br.com.fasete.sisprocessos.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

import br.com.fasete.sisprocessos.basicas.Funcionario;
import br.com.fasete.sisprocessos.util.HibernateUtil;

public class FuncionarioDAO extends GenericDAO<Funcionario>{

	@SuppressWarnings("unchecked")
	public List<Funcionario> listarFuncionariosPorSetor(Long codigoSetor) {
		Session sessao = HibernateUtil.getFabricaDeSessoes().openSession();
		try {
			Criteria consulta = sessao.createCriteria(Funcionario.class);
			consulta.add(Restrictions.eq("setor.codigo", codigoSetor));
			consulta.addOrder(Order.asc("nome"));
			List<Funcionario> resultado = consulta.list();
			return resultado;
		} catch (RuntimeException erro) {
			throw erro;
		} finally {
			sessao.close();
		}
	}
}
