package br.com.helpIT.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpIT.repository.interfaces.RepositorySetor;
import br.com.helpIT.srv.interfaces.SrvSetor;

@Service
public class SrvSetorImpl implements SrvSetor {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositorySetor repositorySetor;

}
