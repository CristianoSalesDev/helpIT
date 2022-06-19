package br.com.helpIT.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.model.classes.Setor;
import br.com.helpIT.repository.interfaces.RepositorySetor;

@Repository
public class DaoSetor extends ImplementacaoCrud<Setor> implements RepositorySetor {

	private static final long serialVersionUID = 1L;

}
