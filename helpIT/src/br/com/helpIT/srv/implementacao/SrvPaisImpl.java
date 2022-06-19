package br.com.helpIT.srv.implementacao;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import br.com.helpIT.repository.interfaces.RepositoryPais;
import br.com.helpIT.srv.interfaces.SrvPais;

@Service
public class SrvPaisImpl implements SrvPais {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private RepositoryPais repositoryPais;

}
