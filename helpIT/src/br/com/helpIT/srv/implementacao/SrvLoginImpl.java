package br.com.helpIT.srv.implementacao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpIT.repository.interfaces.ReposirotyLogin;
import br.com.helpIT.srv.interfaces.SrvLogin;

@Service
public class SrvLoginImpl implements SrvLogin {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ReposirotyLogin repositoryLogin;
	

	@Override
	public boolean autentico(String login, String senha) throws Exception {

		return repositoryLogin.autentico(login, senha);
	}

}
