package br.com.helpIT.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Cidade;
import br.com.helpIT.repository.interfaces.RepositoryCidade;
import br.com.helpIT.srv.interfaces.SrvCidade;

@Controller
public class CidadeController extends ImplementacaoCrud<Cidade> implements InterfaceCrud<Cidade> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvCidade srvCidade;
	
	@Resource
	private RepositoryCidade repositoryCidade;

}
