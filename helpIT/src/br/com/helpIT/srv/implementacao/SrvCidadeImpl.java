package br.com.helpIT.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpIT.repository.interfaces.RepositoryCidade;
import br.com.helpIT.srv.interfaces.SrvCidade;

@Service
public class SrvCidadeImpl implements SrvCidade {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryCidade repositoryCidade;

}
