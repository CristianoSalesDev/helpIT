package br.com.helpIT.project.geral.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Entidade;
import br.com.helpIT.srv.interfaces.SrvEntidade;

@Controller
public class EntidadeController extends ImplementacaoCrud<Entidade> implements InterfaceCrud<Entidade> {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private SrvEntidade srvEntidade;
	
	public Entidade findUserLogado(String userLogado) throws Exception {
		return super.findUninqueByProperty(Entidade.class, userLogado,
				"ent_login", " and entity.ent_inativo is false");
	}
	
	public void updateUltimoAcessoUser(String login) {
		srvEntidade.updateUltimoAcessoUser(login);
	}	
	
	public Date getUltimoAcessoEntidadeLogada(String name) {
		return srvEntidade.getUltimoAcessoEntidadeLogada(name);
	}	

}
