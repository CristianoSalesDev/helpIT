package br.com.helpIT.implementacao.crud;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.helpIT.hibernate.session.HibernateUtil;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Entidade;

@Component
@Transactional
public class ImplementacaoCrud<T> implements InterfaceCrud<T> {

	private static final long serialVersionUID = 1L;
	
	private static SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
	
	@Autowired
	private JdbcTemplateImpl jdbcTemplate;

	@Autowired
	private SimpleJdbcTemplateImpl simpleJdbcTemplate;
	
	@Autowired
	private SimpleJdbcInsertImpl simpleJdbcInsert;
	
	@Autowired
	private SimpleJdbcClassImpl simpleJdbcClass;
	
	@Override
	public void save(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().save(obj);
		executeFlushSession();		
	}

	@Override
	public void persist(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().persist(obj);		
        executeFlushSession();		
	}

	@Override
	public void saveOrUpdate(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().saveOrUpdate(obj);
		executeFlushSession();		
	}

	@Override
	public void update(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().update(obj);
		executeFlushSession();				
	}

	@Override
	public void delete(T obj) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().delete(obj);
		executeFlushSession();		
	}

	/**
	 * Atualiza ou insere objeto no banco TestUnit ok
	 */	
	@Override
	public T merge(T obj) throws Exception {
		validaSessionFactory();
		obj = (T) sessionFactory.getCurrentSession().merge(obj);
		executeFlushSession();
		return obj;
	}

	@Override
	public List<T> findList(Class<T> entidade) throws Exception {
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		query.append("select distinct(entity) from ").append(entidade.getSimpleName()).append(" entity ");
		List<T> lista = sessionFactory.getCurrentSession().createQuery(query.toString()).list();
		return lista;
	}

	@Override
	public Object findById(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		Object obj = sessionFactory.getCurrentSession().load(entidade, id);
		return obj;
	}

	@Override
	public T findByPorId(Class<T> entidade, Long id) throws Exception {
		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession().load(entidade, id);
		return obj;
	}

	@Override
	public List<T> findListByQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(s).list();
		return lista;
	}

	@Override
	public void executeUpdateQueryDinamica(String s) throws Exception {
		validaSessionFactory();
		//validaTransaction();

		sessionFactory.getCurrentSession().createQuery(s).executeUpdate();
		executeFlushSession();		
	}

	@Override
	public void executeUpdateSQLDinamica(String s) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().createSQLQuery(s).executeUpdate();
		executeFlushSession();		
	}

	@Override
	public void clearSession() throws Exception {
		sessionFactory.getCurrentSession().clear();				
	}

	@Override
	public void evict(Object objs) throws Exception {
		validaSessionFactory();
		sessionFactory.getCurrentSession().evict(objs);		
	}

	@Override
	public Session getSession() throws Exception {
		validaSessionFactory();
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<?> getListSQLDinamica(String sql) throws Exception {
		validaSessionFactory();
		List<?> lista = sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return null;
	}
	
	public List<Object[]> getListSQLDinamicaArray(String sql) throws Exception {
		validaSessionFactory();
		
		List<Object[]> lista = (List<Object[]>) sessionFactory.getCurrentSession().createSQLQuery(sql).list();
		return lista;
	}

	@Override
	public JdbcTemplate getJdbcTemplate() {
		
		return jdbcTemplate;
	}

	@Override
	public SimpleJdbcTemplate getSimpleJdbcTemplate() {

		return simpleJdbcTemplate;
	}

	@Override
	public SimpleJdbcInsert getSimpleJdbcInsert() {
		
		return simpleJdbcInsert;
	}
	
	public SimpleJdbcClassImpl getSimpleJdbcClass() {
		return simpleJdbcClass;
	}		

	@Override
	public Long totalRegistro(String tabela) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(1) from ").append(tabela);
		return jdbcTemplate.queryForLong(sql.toString());
	}

	@Override
	public Query obterQuery(String query) throws Exception {
		validaSessionFactory();
		Query queryReturn = sessionFactory.getCurrentSession().createQuery(query.toString());
		return queryReturn;
	}

	/**
	 * Realiza consulta no banco de dados, iniciando o carregamento a partir do
	 * registro passado no paramentro -> iniciaNoRegistro e obtendo maximo de
	 * resultados passados em -> maximoResultado.
	 * 
	 * @param query
	 * @param iniciaNoRegistro
	 * @param maximoResultado
	 * @return List<T>
	 * @throws Exception
	 */		
	@Override
	public List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception {
		validaSessionFactory();
		List<T> lista = new ArrayList<T>();
		lista = sessionFactory.getCurrentSession().createQuery(query).setFirstResult(iniciaNoRegistro).setMaxResults(maximoResultado).list();
		return lista;
	}
	
	private void validaTransaction() {
		if (!sessionFactory.getCurrentSession().getTransaction().isActive())
			sessionFactory.getCurrentSession().beginTransaction();
	}

	private void validaSessionFactory() {
		if (sessionFactory == null) {
			sessionFactory = HibernateUtil.getSessionFactory();
		}

		validaTransaction();
	}	

	public void commitProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().commit();
	}

	public void rollbackProcessoAjax() {
		sessionFactory.getCurrentSession().beginTransaction().rollback();
	}
	
	/**
	 * Roda instantaneamente o sql no banco de dados
	 * 
	 * @throws Exception
	 */
	private void executeFlushSession() throws Exception {
		sessionFactory.getCurrentSession().flush();
	}

	public T findUninqueByProperty(Class<T> entidade, Object valor, String atributo, String condicao) throws Exception {
		
		validaSessionFactory();
		StringBuilder query = new StringBuilder();
		
		query.append("select entity from ").append(entidade.getSimpleName())
		.append(" entity where entity.").append(atributo)
		.append(" = '").append(valor).append("' ").append(condicao);		
		
		T obj = (T) this.findUniqueByQueryDinamica(query.toString());		
		
		return obj;
		
		
	}
	
	@Override
	public T findUniqueByQueryDinamica(String query) throws Exception {
		validaSessionFactory();
		T obj = (T) sessionFactory.getCurrentSession()
				.createQuery(query.toString()).uniqueResult();
		return obj;
	}	

}
