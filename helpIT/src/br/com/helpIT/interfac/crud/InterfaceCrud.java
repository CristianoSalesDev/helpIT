package br.com.helpIT.interfac.crud;

import java.io.Serializable;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public interface InterfaceCrud<T> extends Serializable {

	// Salva os dados
	void save(T obj) throws Exception;
	
	// Salva os dados
	void persist(T obj) throws Exception;
	
	// Salva ou atualiza dos dados
	void saveOrUpdate(T obj) throws Exception;
	
	// Somente atualiza os dados
	void update(T obj) throws Exception;

	// Deleta os dados
	void delete(T obj) throws Exception;
	
	// Salva ou atualiza os dados e retorna o objeto em estado persistente 
	T merge(T obj) throws Exception;
	
	// carrega a lista de dados de uma determinada classe
	List<T> findList(Class<T> objs) throws Exception;
	
    Object findById(Class<T> entidade, Long id) throws Exception;
	
	T findByPorId(Class<T> entidade, Long id) throws Exception;
	
	List<T> findListByQueryDinamica(String s) throws Exception;
	
	// Executa update com HQL
	void executeUpdateQueryDinamica(String s) throws Exception;
	
	// Executa update com SQL puro
	void executeUpdateSQLDinamica(String s) throws Exception;
	
	// Limpa a sessão do hibernate
	void clearSession() throws Exception;
	
	// Retira um objeto da sessão do hibernate
	void evict(Object objs) throws Exception;
	
	Session getSession() throws Exception;
	
	List<?> getListSQLDinamica(String sql) throws Exception;
	
	// JDBC do Spring
	JdbcTemplate getJdbcTemplate();

	SimpleJdbcTemplate getSimpleJdbcTemplate();

	SimpleJdbcInsert getSimpleJdbcInsert();
	
    Long totalRegistro(String tabela) throws Exception;	
	
	Query obterQuery(String query) throws Exception;
	
	// Carregando dinamico com JSF e PrimeFaces
    List<T> findListByQueryDinamica(String query, int iniciaNoRegistro, int maximoResultado) throws Exception;

	T findUniqueByQueryDinamica(String query) throws Exception; 		
}
