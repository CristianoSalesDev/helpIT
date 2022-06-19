package br.com.helpIT.repository.interfaces;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

@Repository
public interface ReposirotyLogin extends Serializable {

	boolean autentico(String login, String senha)  throws Exception;	
}
