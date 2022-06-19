package br.com.helpIT.dao.implementacao;

import org.springframework.stereotype.Repository;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.model.classes.Cargo;
import br.com.helpIT.repository.interfaces.RepositoryCargo;

@Repository
public class DaoCargo extends ImplementacaoCrud<Cargo> implements RepositoryCargo {

	private static final long serialVersionUID = 1L;

}
