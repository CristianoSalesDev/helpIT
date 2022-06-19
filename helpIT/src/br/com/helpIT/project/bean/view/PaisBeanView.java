package br.com.helpIT.project.bean.view;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import br.com.helpIT.interfac.crud.InterfaceCrud;
import br.com.helpIT.model.classes.Pais;
import br.com.helpIT.project.bean.geral.BeanManagedViewAbstract;
import br.com.helpIT.project.geral.controller.PaisController;

@Controller
@Scope(value = "session")
@ManagedBean(name = "paisBeanView")
public class PaisBeanView extends BeanManagedViewAbstract {

	private static final long serialVersionUID = 1L;
	
	@Autowired
	private PaisController paisController;

	@Override
	protected Class<Pais> getClassImplement() {
		return Pais.class;
	}

	@Override
	protected InterfaceCrud<Pais> getController() {
		return paisController;
	}

	@Override
	public String condicaoAndParaPesquisa() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
