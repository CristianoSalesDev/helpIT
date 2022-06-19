package br.com.helpIT.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.model.classes.Cidade;
import br.com.helpIT.repository.interfaces.RepositoryCidade;

@Repository
public class DaoCidade extends ImplementacaoCrud<Cidade> implements RepositoryCidade {

	private static final long serialVersionUID = 1L;

}
