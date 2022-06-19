package br.com.helpIT.project.geral.controller;

import javax.annotation.Resource;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Pais;
import br.com.helpIT.repository.interfaces.RepositoryPais;
import br.com.helpIT.srv.interfaces.SrvPais;

public class PaisController extends ImplementacaoCrud<Pais> implements InterfaceCrud<Pais> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvPais srvPais;
	
	@Resource
	private RepositoryPais repositoryPais;

}
