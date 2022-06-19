package br.com.helpIT.project.geral.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Cargo;
import br.com.helpIT.repository.interfaces.RepositoryCargo;
import br.com.helpIT.srv.interfaces.SrvCargo;

@Controller
public class CargoController extends ImplementacaoCrud<Cargo> implements InterfaceCrud<Cargo> {

	private static final long serialVersionUID = 1L;
	
	@Resource
	private SrvCargo srvCargo;
	
	@Resource
	private RepositoryCargo repositoryCargo;

}
