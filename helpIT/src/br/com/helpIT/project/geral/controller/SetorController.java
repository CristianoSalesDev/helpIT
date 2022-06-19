package br.com.helpIT.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Setor;
import br.com.helpIT.repository.interfaces.RepositorySetor;
import br.com.helpIT.srv.interfaces.SrvSetor;

@Controller
public class SetorController extends ImplementacaoCrud<Setor> implements InterfaceCrud<Setor> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvSetor srvSetor;
	
	@Resource
	private RepositorySetor repositorySetor;

}
