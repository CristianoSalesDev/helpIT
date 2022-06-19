package br.com.helpIT.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpIT.repository.interfaces.RepositoryCargo;
import br.com.helpIT.srv.interfaces.SrvCargo;

@Service
public class SrvCargoImpl implements SrvCargo {
	
	private static final long serialVersionUID = 1L;

	@Autowired
	private RepositoryCargo repositoryCargo;

}
