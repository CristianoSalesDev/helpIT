package br.com.helpIT.project.geral.controller;

import org.springframework.stereotype.Controller;

import br.com.helpIT.implementacao.crud.ImplementacaoCrud;
import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Mensagem;

@Controller
public class MensagemController extends ImplementacaoCrud<Mensagem> implements
    InterfaceCrud<Mensagem> {

	private static final long serialVersionUID = 1L;
	
	@Override
	public Mensagem merge(Mensagem obj) throws Exception {
	
		return super.merge(obj);
	}
}
