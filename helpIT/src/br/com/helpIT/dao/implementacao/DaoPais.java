package br.com.helpIT.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.model.classes.Pais;
import br.com.helpIT.repository.interfaces.RepositoryPais;

@Repository
public class DaoPais extends ImplementacaoCrud<Pais> implements RepositoryPais {

	private static final long serialVersionUID = 1L;

}
