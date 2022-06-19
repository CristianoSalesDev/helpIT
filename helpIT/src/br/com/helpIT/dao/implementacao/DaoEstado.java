package br.com.helpIT.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.model.classes.Estado;
import br.com.helpIT.repository.interfaces.RepositoryEstado;

@Repository
public class DaoEstado extends ImplementacaoCrud<Estado> implements RepositoryEstado {

	private static final long serialVersionUID = 1L;

}
