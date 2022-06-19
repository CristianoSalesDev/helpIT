package br.com.helpIT.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpIT.repository.interfaces.RepositoryEstado;
import br.com.helpIT.srv.interfaces.SrvEstado;

@Service
public class SrvEstadoImpl implements SrvEstado {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private RepositoryEstado repositoryEstado;

}
